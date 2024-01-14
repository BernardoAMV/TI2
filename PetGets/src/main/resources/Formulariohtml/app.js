
async function enviarImagem() {
  
  

  imgUrl = await getImgURL();

  console.log(imgUrl);

  return imgUrl;
}

async function  getImgURL(){

  const fileInput = document.getElementById('imagem');
  
  const file = fileInput.files[0];
  var imgUrl;

  const options = {
    method: "post",
    headers:{
      Authorization: "Client-ID b573c0344482155"
    },
    body: file

  }

  var link;
  
  await fetch("https://api.imgur.com/3/image/", options)
  .then(response => {
    if (!response.ok) {
      throw new Error('Erro ao enviar a imagem para o Imgur.');
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
    link = data.data.link;
    console.log(link);
    // Faça o que desejar com imgUrl aqui dentro
  })
  .catch(error => {
    console.error('Erro:', error);
  });
  return link;
}


async function salvarDados() {
  // Recupera os valores dos campos de texto
  var nomeAnimal = document.getElementById("nome-animal").value;
  var especie = document.getElementById("tipo").value;
  var sexo = document.getElementById("sexo").value;
  var idade = document.getElementById("idade").value;
  var raca = document.getElementById("raca-animal").value;
  var descricao = document.getElementById("descricao-animal").value;
  var responsavel = JSON.parse(localStorage.getItem('dadosUser')).cpf;
  var contatoResponsavel = JSON.parse(localStorage.getItem('dadosUser')).contato;
  var nomeResponsavel = JSON.parse(localStorage.getItem('dadosUser')).nome;
  console.log(responsavel);
  var imagem = await enviarImagem();
  console.log(imagem);
if(especie == "Outro")
  especie = document.getElementById("outroTipo").value
  // Verifica se os campos estão preenchidos
  if (nomeAnimal && especie && sexo && idade && raca && descricao && imagem && responsavel && contatoResponsavel && nomeResponsavel) {
    // Cria um objeto com os dados
    var dados = {
      nomeAnimal: nomeAnimal,
      especieAnimal: especie,
      sexoDoAnimal: sexo,
      idadeAnimal: idade,
      racaAnimal: raca,
      descricaoAnimal: descricao,
      fotoAnimal: imagem,
      responsavel: responsavel,
      contatoResponsavel: contatoResponsavel,
      nomeResponsavel: nomeResponsavel,
    };

    // Converte o objeto em uma string JSON
    const options = {
      method : 'POST',
      body : JSON.stringify(dados)

    };
    var usuarios = fetch('http://localhost:5554/animal/insert', options)
.then(response => response.json())
.then(data => {
    console.log('Usuário inserido com sucesso:', data);
    // Você pode realizar outras ações aqui após a inserção bem-sucedida
})
.catch(error => {
    console.error('Erro ao inserir usuário:', error);
});
  
  localStorage.setItem('Animais', JSON.stringify(dados));

    // Exibe uma mensagem de sucesso
    alert("Conta criada com sucesso!");

    // Limpa os campos do formulário
    document.getElementById("nomeInstituicao").value = "";
    document.getElementById("cnpjInput").value = "";
    document.getElementById("senhaInput").value = "";
    document.getElementById("contato").value = "";

    var cadastroDiv = document.querySelector('.cadastro');
    var overlayDiv = document.querySelector('.overlay');

    cadastroDiv.remove();
    overlayDiv.style.display = 'none';


  } else {
    // Exibe uma mensagem de erro se algum campo estiver vazio
    alert("Preencha todos os campos do formulário..");
  }
}