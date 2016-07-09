
public class LetterInventory {
	private int[] letterCounts = new int[26];
	
	public LetterInventory(String data){
		for (int i = 0; i < 26; i++){
			letterCounts[i] = 0;
		}
		for (int i = 0; i < data.length(); i++){
			if (Character.isLetter(data.charAt(i))){
				int x = Character.toLowerCase(data.charAt(i)) - 97;
				letterCounts[x]++;
			}
		}
	}
	
	public int get(char letter){
		if (!Character.isLetter(letter)) throw new IllegalArgumentException();
		return letterCounts[Character.toLowerCase(letter) - 97];
	}
	
	public void set(char letter, int value){
		if (!Character.isLetter(letter)) throw new IllegalArgumentException();
		letterCounts[Character.toLowerCase(letter) - 97] = value;
	}
	
	public int size(){
		int size = 0;
		for (int i = 0; i < 26; i++){
			size += letterCounts[i];
		}
		return size;
	}
	
	public boolean isEmpty(){
		for (int i : letterCounts){
			if (i != 0) return false;
		}
		return true;
	}
	
	public String toString(){
		String result = "[";
		for (int i = 0; i < 26; i++){
			for (int j = 0; j < letterCounts[i]; j++){
				result += (char) (i + 97);
			}
		}
		result += "]";
		return result;
	}
	
	public LetterInventory add(LetterInventory other){
		return new LetterInventory(this.toString() + other.toString());
	}
	
	public LetterInventory subtract(LetterInventory other){
		int[] result = new int[26];
		for (int i = 0; i < 26; i++){
			if (get((char)(i + 97)) - other.get((char)(i + 97)) < 0){
				return null;
			} else result[i] = get((char)(i + 97)) - other.get((char)(i + 97));
		}
		String res = "";
		
		for (int i = 0; i < 26; i++){
			for (int j = 0; j < result[i]; j++){
				res += (char)(i + 97);
			}
		}
		return new LetterInventory(res);
	}
}
