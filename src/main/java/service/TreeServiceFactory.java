package service;

public class TreeServiceFactory {

	private TreeServiceFactory() {
		//
	}

	public static ITreeService getService(String type) {
		ITreeService service;

		if ("1".equalsIgnoreCase(type)) {
			service = new AddChildTreeService();
			return service;
		} else if ("2".equalsIgnoreCase(type)) {
			service = new AddSpouseTreeService();
			return service;
		} else if ("3".equalsIgnoreCase(type)) {
			service = new QueryTreeService();
			return service;
		}
		return null;
	}
}
