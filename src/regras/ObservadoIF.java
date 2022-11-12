package regras;

public interface ObservadoIF 
{
	public void add(ObservadorIF obs);

	public void remove(ObservadorIF obs);
	
	public int [][]get();
	
	public void set_coord_possiveis(int [][] coords);
}
