package util;


public abstract class ParameterCrypter {

	private static CryptoBill Key_Parent() {
//		CryptoBill Key_Parent = new CryptoBill(
//				"E5TYWsuio9210LLnMzxx11HgeuiijFFvXbmEarty");
		CryptoBill Key_Parent = new CryptoBill(
				"Ke3yp4r3nt");
		return Key_Parent;
	}

	public static String onEncrypt(String desDecrypted) {
		// Encrypt the string
		String desEncrypted = "";
		if (desDecrypted != null && !desDecrypted.equals("")) {			
			desEncrypted = Key_Parent().encrypt(desDecrypted);
		}

		return desEncrypted;
	}

	public static String onDecrypt(String desEncrypted) {
		// Decrypt the string
		String desDecrypted = "";
		if (desEncrypted != null && !desEncrypted.equals("")) {
			desDecrypted = Key_Parent().decrypt(desEncrypted);
		}

		return desDecrypted;
	}

	public static boolean isCheckEncryption(String encrypt) {
		boolean checker = false;
		if (encrypt != null && !encrypt.equals("")) {
			String des = onDecrypt(encrypt);

			if (des != null) {
				String enc = onEncrypt(des);
				if (encrypt.equalsIgnoreCase(enc)) {
					checker = true;
				}
			}
		}

		return checker;
	}

	public static void main(String[] args) {
//		System.out.println("Enc = "
//				+ ParameterCrypter.onEncrypt("__report=kecamatan_report.rptdesign&__pageFooterFloatFlag=false"));
//		System.out.println("Dec = "
//				+ ParameterCrypter.onDecrypt("iNPUpS9cpzdCABmCVAJ+8Pz7qpGBMIShvf7/Id935LbnIbcvy6O50lxpQQWC8eLQ1uN+9xhQ+gE="));

		System.out.println("Enc = "
				+ ParameterCrypter.onEncrypt("Test Enkripsi"));
		System.out.println("Dec = "
				+ ParameterCrypter.onDecrypt("cp4dnmr3cOYFyIRbd8z1XA=="));

		boolean check = isCheckEncryption("100");
		System.out.println("check = " + check);
	}
}
