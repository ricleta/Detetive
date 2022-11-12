package regras;

public interface ObservadorIF 
{
	public void notify_dado_jogado(ObservadoIF ob);
	
	public void notify_jogador_moveu(ObservadoIF ob);
}
