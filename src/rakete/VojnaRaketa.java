package rakete;

import interfejsi.LovljenjeMete;

public class VojnaRaketa extends Raketa implements LovljenjeMete {

	public VojnaRaketa() {
		super();
	}

	public VojnaRaketa(Integer dometRakete, Integer visinaLetenja) {
		super(dometRakete, visinaLetenja);
		this.oznaka = "VR";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
