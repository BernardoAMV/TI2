package exercicio4;

import com.microsoft.azure.cognitiveservices.vision.face.*;
import com.microsoft.azure.cognitiveservices.vision.face.models.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    // Recognition model 3 was released in 2020 May
    private static final String RECOGNITION_MODEL3 = RecognitionModel.RECOGNITION_03;

    public static FaceClient Authenticate(String endpoint, String key) {
        return new FaceClient(new ApiKeyServiceClientCredentials(key)) {{
            setEndpoint(endpoint);
        }};
    }

    private static CompletableFuture<List<DetectedFace>> DetectFaceRecognize(IFaceClient faceClient, String url, String recognition_model) {
        return faceClient.face().detectWithUrlAsync(url, new DetectWithUrlOptionalParameter().withRecognitionModel(recognition_model).withDetectionModel(DetectionModel.DETECTION_02)).thenApply(detectedFaces -> {
            System.out.println(detectedFaces.size() + " face(s) detectada(s) na imagem `" + new File(url).getName() + "`");
            return detectedFaces;
        });
    }

    public static CompletableFuture<Void> FindSimilar(IFaceClient client, String recognition_model) {
        System.out.println("========Achar Similares========");
        System.out.println();
        System.out.println("Insira o link da face base(links muito grandes podem causar erros):\n");
        Scanner scanner = new Scanner(System.in);
        String sourceImageFileName = scanner.nextLine();
        System.out.println("Insira o link das possíveis faces similares e digite FIM quando acabar (links muito grandes podem causar erros):\n");
        List<String> targetImageFileNames = new ArrayList<>();
        String aux = "";
        int i = 1;
        do {
            System.out.println("Imagem " + i + ":\n");
            aux = scanner.nextLine();
            if (!aux.equals("FIM")) {
                targetImageFileNames.add(aux);
            }
            i++;
        } while (!aux.equals("FIM"));

        List<UUID> targetFaceIds = new ArrayList<>();
        for (String targetImageFileName : targetImageFileNames) {
            // Detect faces from target image url.
            CompletableFuture<List<DetectedFace>> detectFuture = DetectFaceRecognize(client, targetImageFileName, recognition_model);
            List<DetectedFace> faces = detectFuture.join();
            // Add detected faceId to list of UUIDs.
            targetFaceIds.add(faces.get(0).faceId());
        }

        // Detect faces from source image url.
        CompletableFuture<List<DetectedFace>> detectSourceFuture = DetectFaceRecognize(client, sourceImageFileName, recognition_model);
        List<DetectedFace> detectedFaces = detectSourceFuture.join();
        System.out.println();

        // Find a similar face(s) in the list of IDs. Comparing only the first in list for testing purposes.
        CompletableFuture<List<SimilarFace>> findSimilarFuture = client.face().findSimilarWithHttpMessagesAsync(detectedFaces.get(0).faceId(), null, null, targetFaceIds, null);
        List<SimilarFace> similarResults = findSimilarFuture.join().body();
        i = 1;
        for (SimilarFace similarResult : similarResults) {
            System.out.println("A imagem " + i + " com o FaceID:" + similarResult.faceId() + " é similar a imagem base com a confiança: " + similarResult.confidence() + ".");
            i++;
        }
        System.out.println();
        return CompletableFuture.completedFuture(null);
    }

    public static void main(String[] args) {
        // From your Face subscription in the Azure portal, get your subscription key and endpoint.
        System.out.println("Insira a URL da sua aplicação no Azure:\n");
        Scanner scanner = new Scanner(System.in);
        String urlServico = scanner.nextLine();
        System.out.println("Insira a chave da sua aplicação no Azure:\n");
        String chaveServico = scanner.nextLine();

        // Authenticate.
        IFaceClient client = Authenticate(urlServico, chaveServico);
        FindSimilar(client, RECOGNITION_MODEL3).join();
    }
}

