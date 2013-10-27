package Lab1;

public class Login {
	private String login;
	private String password;

	/**
	 * 
	 * @param _login
	 *            login jaki dana instancja klasy bêdiz eprzechowywaæ
	 * @param _password
	 *            has³o jakie dana instancja klasy bêdiz eprzechowywaæ
	 */
	public Login(String _login, String _password) {
		login = _login;
		password = _password;
	}

	/**
	 * 
	 * @param _login
	 *            login do porównania z przechowywanym wewn¹trz obiektu
	 * @param _password
	 *            has³o do porównania z przechowywanym wewnatrz obiektu
	 * @return prawda, gdy login i has³o zgadzaja sie, fa³sz gdy albo login albo
	 *         has³o nie pasuje do tych rpzechowywanych przez instancjê kalsy
	 */
	public boolean check(String _login, String _password) {
		return (login.equals(_login) && password.equals(_password));
	}
}
