DROP TABLE IF EXISTS reservation_participants;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS meeting_rooms;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

CREATE TABLE meeting_rooms (
                               id INTEGER PRIMARY KEY AUTOINCREMENT,
                               name VARCHAR(255) NOT NULL,
                               capacity INTEGER NOT NULL,
                               location VARCHAR(255) NOT NULL,
                               is_active BOOLEAN NOT NULL DEFAULT 1
);

CREATE TABLE reservations (
                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                              title VARCHAR(255) NOT NULL,
                              start_time TIMESTAMP NOT NULL,
                              end_time TIMESTAMP NOT NULL,
                              purpose VARCHAR(255) NOT NULL,
                              description TEXT,
                              number_of_attendees INTEGER NOT NULL,
                              is_cancelled BOOLEAN NOT NULL DEFAULT 0,
                              status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                              meeting_room_id INTEGER NOT NULL,
                              organizer_id INTEGER NOT NULL,
                              FOREIGN KEY (meeting_room_id) REFERENCES meeting_rooms(id),
                              FOREIGN KEY (organizer_id) REFERENCES users(id)
);

CREATE TABLE equipment (
                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                           name VARCHAR(255) NOT NULL,
                           description TEXT,
                           is_available BOOLEAN NOT NULL DEFAULT 1,
                           meeting_room_id INTEGER NULL,
                           FOREIGN KEY (meeting_room_id) REFERENCES meeting_rooms(id)
);

CREATE TABLE reservation_participants (
                                          reservation_id INTEGER NOT NULL,
                                          user_id INTEGER NOT NULL,
                                          PRIMARY KEY (reservation_id, user_id),
                                          FOREIGN KEY (reservation_id) REFERENCES reservations(id),
                                          FOREIGN KEY (user_id) REFERENCES users(id)
);