package gpt.model;

public class GPT{
	
	private String API_KEY = "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";
	private String url = "https://api.openai.com/v1/chat/completions";
	private String model = "gpt-3.5-turbo";
	
	public GPT() {}
	
	public String getAPI_KEY() {
		return API_KEY;
	}
	public String getUrl() {
		return url;
	}
	public String getModel() {
		return model;
	}
	
}