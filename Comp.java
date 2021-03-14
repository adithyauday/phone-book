package comp9103;

public class Comp {
	public static void main(String[] args) {
		// TODO:
		if(args.length == 4){
			PhoneProcessor pp = new PhoneProcessor(args);
			pp.loadInput();
			pp.readInstruction();
		}
	}
}
