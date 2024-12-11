import java.util.Arrays;

public class Conta {
    int numero;
    double saldo;
    double limite;
    double[] extrato;
    int operacoesRealizadas;



    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        this.limite = 100;
        this.extrato = new double[10];
        this.operacoesRealizadas = 0;
    }


    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo+limite;
    }


    public double getLimite() {
        return limite;
    }

    public boolean sacar(double valor) {
        if (valor <= 0 || (saldo + limite) < valor) {
            return false;
        }
        if (valor <= saldo) {
            saldo -= valor;
        }else {
          limite -= (valor - saldo);
          saldo = 0;
        }
            extrato[operacoesRealizadas] = valor * -1;
            operacoesRealizadas++;
            return true;
    }


    public boolean depositar(double valor) {
        if (valor <= 0) {
            return false;
        }
        if (limite < 100) {
            limite += valor;

            if (limite > 100) {
                saldo += (limite - 100);
                limite = 100;
            }
        }else {
            saldo += valor;
        }
        extrato[operacoesRealizadas] = valor;
        operacoesRealizadas++;
        return true;
    }


    public boolean transferir(Conta destino, double valor) {
        if (valor <= 0 || destino == null || (saldo + limite) < valor ){
            return false;
        }
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }else {
            return false;
        }
    }


    public double[] verExtrato() {
        return Arrays.copyOfRange(extrato, 0, operacoesRealizadas);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", limite=" + limite+
                '}';
    }
}