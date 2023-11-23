use admin;
db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [ { role: "userAdminAnyDatabase", db: "admin" },
            { role: "dbAdminAnyDatabase", db: "admin" },
            { role: "readWriteAnyDatabase", db: "admin" } ]
    }
);
db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [
            {
                role: "readWrite",
                db: "notes"
            }
        ]
    }
);
db.createCollection("notes");
