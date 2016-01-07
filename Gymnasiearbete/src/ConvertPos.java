
public class ConvertPos {
	// Konverterar en x-koordinat från meter till pixlar
	public static int xmToxp(double xm) {
		double xp = (Physics2D.FWIDTH_PX / Physics2D.FWIDTH_M) * xm;
		return (int) xp;
	}

	// Konverterar en y-koordinat från meter till pixlar
	public static int ymToyp(double ym) {
		double yp = -((Physics2D.FHEIGHT_PX / Physics2D.FHEIGHT_M) * ym) + Physics2D.FHEIGHT_PX;
		return (int) yp;
	}

	// Konverterar en x-koordinat från pixlar till meter
	public static double xpToxm(double xp) {
		double xm = xp / (Physics2D.FWIDTH_PX / Physics2D.FWIDTH_M);
		return xm;
	}

	public static double ypToym(double yp) {
		double ym = -((yp - Physics2D.FHEIGHT_PX) / (Physics2D.FHEIGHT_PX / Physics2D.FHEIGHT_M));
		return ym;
	}
}
