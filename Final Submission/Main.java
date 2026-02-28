public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main users.txt item.txt commands.txt");
            return;
        }

        String usersFile = args[0];
        String itemsFile = args[1];
        String commandsFile = args[2];

        try {
            MyBazaar bazaar = new MyBazaar();

            bazaar.loadUsers(usersFile);
            bazaar.loadItems(itemsFile);
            bazaar.processCommands(commandsFile);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}