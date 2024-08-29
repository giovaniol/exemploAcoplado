import java.util.ArrayList;
import java.util.List;


class BancoApp {
    private List<Pessoa> pessoas;
    private List<Conta> contas;

    public BancoApp() {
        this.pessoas = new ArrayList<>();
        this.contas = new ArrayList<>();
    }


    public void criarPessoa(String nome, String cpf) {
        Pessoa pessoa = new Pessoa(nome, cpf);
        pessoas.add(pessoa);
    }


    public void criarConta(String cpf, String numeroConta, double saldoInicial) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        if (pessoa != null) {
            Conta conta = new Conta(numeroConta, saldoInicial, pessoa);
            contas.add(conta);
            pessoa.adicionarConta(conta);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }


    public void depositar(String numeroConta, double valor) {
        Conta conta = buscarContaPorNumero(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }


    public void sacar(String numeroConta, double valor) {
        Conta conta = buscarContaPorNumero(numeroConta);
        if (conta != null) {
            conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }


    public void consultarSaldo(String numeroConta) {
        Conta conta = buscarContaPorNumero(numeroConta);
        if (conta != null) {
            System.out.println("Saldo da conta " + numeroConta + ": " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }


    private Pessoa buscarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    private Conta buscarContaPorNumero(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }


    class Pessoa {
        private String nome;
        private String cpf;
        private List<Conta> contas;

        public Pessoa(String nome, String cpf) {
            this.nome = nome;
            this.cpf = cpf;
            this.contas = new ArrayList<>();
        }

        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void adicionarConta(Conta conta) {
            contas.add(conta);
        }

        public List<Conta> getContas() {
            return contas;
        }
    }


    class Conta {
        private String numero;
        private double saldo;
        private Pessoa titular;

        public Conta(String numero, double saldoInicial, Pessoa titular) {
            this.numero = numero;
            this.saldo = saldoInicial;
            this.titular = titular;
        }

        public String getNumero() {
            return numero;
        }

        public double getSaldo() {
            return saldo;
        }

        public Pessoa getTitular() {
            return titular;
        }

        public void depositar(double valor) {
            saldo += valor;
        }

        public void sacar(double valor) {
            if (valor > saldo) {
                System.out.println("Saldo insuficiente.");
            } else {
                saldo -= valor;
            }
        }

        public static void main(String[] args) {
            BancoApp bancoApp = new BancoApp();


            bancoApp.criarPessoa("João Silva", "123.456.789-00");
            bancoApp.criarConta("123.456.789-00", "001", 1000.0);

            bancoApp.criarPessoa("Maria Oliveira", "987.654.321-00");
            bancoApp.criarConta("987.654.321-00", "002", 1500.0);


            bancoApp.depositar("001", 500.0);
            bancoApp.sacar("002", 200.0);


            bancoApp.consultarSaldo("001");
            bancoApp.consultarSaldo("002");
        }
    }
}

