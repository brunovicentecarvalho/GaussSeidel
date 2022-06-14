public class Gauss {
    public double[][] matriz;
    public double[] resultados;
    public int quantidadeLinhas;

    public Gauss(double[][] matriz, double[] resultados){
        this.matriz = matriz;
        this.resultados = resultados;
        this.quantidadeLinhas = resultados.length;
    }

    public double[] metodoGauss (){
        for (int i = 0; i < this.quantidadeLinhas; i++){
            int y = i;
            for (int j = i + 1;j< this.quantidadeLinhas;j++){
                certificaMaiorValorAcima(i,j,y);
            }
            retornaLinhaReferencia(i,y);
            retornaValorReferencia(i,y);
            garanteValorNaoNulo(i);
            for (int j= i+ 1; j < quantidadeLinhas; j ++){
                double x = divide(i,j);
                this.resultados[j] -= x * resultados[i];
                for (int k = i; k < quantidadeLinhas; k++) {
                    matriz[j][k] -= x * matriz[i][k];
                }
            }
        }
        return solve(new double[quantidadeLinhas]);
    }
    public void retornaLinhaReferencia(int i, int y){
        double[] aux = this.matriz[i];
        this.matriz[i] = this.matriz[y];
        this.matriz[y] = aux;
    }
    public double retornaValorReferencia(int i, int y){
        double aux = this.resultados[i];
        this.resultados[i] = this.resultados[y];
        this.resultados[y] = aux;
        return aux;
    }
    public double divide(int i, int j){
        return matriz[j][i] / matriz[i][i];
    }
    public void garanteValorNaoNulo(int i){
        if(matriz[i][i]==0){
            throw new RuntimeException("Configuração ilegal de matriz");
        }
    }
    public void certificaMaiorValorAcima(int i, int j, int y){
        if(Math.abs(this.matriz[j][i]) > Math.abs(this.matriz[y][i])){
            y = j;
        }
    }
    public double[] solve(double[] x){
        for (int i = x.length -1; i >= 0; i++) {
            double z = 0;
            for (int j = i+1; j < x.length; j++) {
                z += this.matriz[i][j] * x[j];
            }
            x[i] = this.resultados[i] - (z/matriz[i][i]);
        }
        return x;
    }
}
