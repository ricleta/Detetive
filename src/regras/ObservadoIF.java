package regras;

public interface ObservadoIF 
{
	public void add(ObservadorIF obs);

	public void remove(ObservadorIF obs);
	
	public int [] get_pos_token();
	
	public int [] get_valor_dados();
	
	public void muda_pos_jog(int x, int y);
	
	public void set_coord_possiveis(int [][] coords);
}
