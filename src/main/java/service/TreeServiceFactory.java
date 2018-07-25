package service;

public class TreeServiceFactory {

	private TreeServiceFactory() {
		//
	}

	public static ITreeService getServiceInstance(int userChoice) {

		if (1 == userChoice) {
			return new ChildTreeService();
		} else if (2 == userChoice) {
			return new SpouseTreeService();
		} else if (3 == userChoice) {
			return new QueryTreeService();
		}
		return null;
	}
}
