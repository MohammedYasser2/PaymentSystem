public class Transaction {
    String payType;
    public Transaction(String payType,User user,String serviceName,String spName,String card){
        this.payType=payType;
        Pay pay;
        if (payType.equals("Credit")){
            pay=new Credit(card,user,serviceName,spName);
        }
        else if (payType.equals("Wallet")){
            pay=new Wallet();
            pay.pay(user,serviceName,spName);
        }
    }
}
