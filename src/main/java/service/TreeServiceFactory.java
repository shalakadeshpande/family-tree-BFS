package service;

public class TreeServiceFactory {

	private TreeServiceFactory() {
		//
	}

	public static ITreeService getServiceInstance(String userChoice) {

		if ("1".equals(userChoice)) {
			return new ChildTreeService();
		} else if ("2".equals(userChoice)) {
			return new SpouseTreeService();
		} else if ("3".equals(userChoice)) {
			return new QueryTreeService();
		}
		return null;
	}
}
