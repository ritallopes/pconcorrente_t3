public class WaitNotify { //Exemplo do uso do wait/notify
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            Aluno a = new Aluno();
            a.start();
        }
    }
}
