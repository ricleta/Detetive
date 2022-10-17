package regras;

class AuxController extends Controller {

	AuxRegras r;
	public AuxController(int n_jogadores) {
		super(n_jogadores);
		r = new AuxRegras(n_jogadores);
	}
}
