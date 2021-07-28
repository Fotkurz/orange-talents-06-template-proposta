package br.com.zupacademy.guilherme.proposta.domain;

public enum WalletType {
    PAYPAL,
    SAMSUNG_PAY;

    public static boolean isField(String type) {
        WalletType[] list = values();
        for(WalletType x : list) {
            if(x.toString().equals(type)) return true;
        }
        return false;
    }

}
