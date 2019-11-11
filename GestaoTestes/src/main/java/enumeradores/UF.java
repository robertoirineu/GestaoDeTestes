package enumeradores;

public enum UF {
	AC("AC"), AL("AL"), AP("AP"), AM("AM"), BA("BA"), CE("CE"), DF("DF"), ES("ES"), GO("GO"), MA("MA"), MT("MT"), MS("MS"), MG("MG"), PA("PA"), PB("PB"), PR("PR"), PE("PE") ,PI("PI"), RJ("RJ"), RN("RN"), RS("RS"), RO("RO"), RR("RR"), SC("SC"), SP("SP"), SE("SE"), TO("TO");
private String uf;
	
	UF(String uf) {
		this.uf = uf;
	}
		
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
