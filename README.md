# Running Instructions
Contribution to Community API

Running the cc-api project is a straightforward process. Start by extracting the attached file from your email and navigate to the 'cc-api' folder, where you'll find all the source code conveniently located.

All you need is to run the following command:

**Note:** Prior to launching the containers, be sure to halt the psql service on your machine to prevent any potential port conflicts.

**Note:** The command you need to use may vary depending on the version of Docker Compose installed on your machine. In some cases, you might need to utilize the older command 'docker-compose.'

The cc-api application and the Postgres container will be automatically fetched from Docker Hub and initialized on your machine.

Once the containers are up and running, simply access the project via the following URL: **http://localhost:8080/cc-api/**. You'll automatically be redirected to the Swagger page.

In the first access, you will be required to fill a user/pass credentials. You can use the word **'user'** for both the username and password fields. Subsequently, you'll gain access to the API endpoints.

![Screenshot from 2023-09-05 03-34-37](https://github.com/kelciocajueiro/cc-api/assets/1596545/5608b776-8ae4-491f-b9be-44824164a520)

**Reminder:** To populate the database with Spreadsheet data, it's essential to start by accessing the 'v1/data-load' endpoint, as previously noted.

After that, feel free to try all the other endpoints.

**Enjoy!** 🙂
