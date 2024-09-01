module MovieStoreManagementSystem {
    requires java.sql; // Required for JDBC operations
    exports dao;
    exports model;
    exports service;
}