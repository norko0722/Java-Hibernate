INSERT INTO users (first_name, last_name, email, password, role) VALUES
                                                                     ('John', 'Doe', 'john.doe@example.com', 'password123', 'ADMIN'),
                                                                     ('Jane', 'Smith', 'jane.smith@example.com', 'password123', 'USER'),
                                                                     ('Bob', 'Johnson', 'bob.johnson@example.com', 'password123', 'USER'),
                                                                     ('Test', 'User', 'test@example.com', 'password', 'USER');


INSERT INTO meeting_rooms (name, capacity, location, is_active) VALUES
                                                                    ('Conference Room A', 10, 'First Floor', 1),
                                                                    ('Conference Room B', 20, 'Second Floor', 1),
                                                                    ('Board Room', 15, 'Third Floor', 1),
                                                                    ('Training Room', 30, 'First Floor', 1);


INSERT INTO equipment (name, description, meeting_room_id) VALUES
                                                               ('Projector', 'HD Projector with HDMI input', 1),
                                                               ('Whiteboard', 'Large whiteboard with markers', 1),
                                                               ('Video Conference System', '4K camera with microphone array', 2),
                                                               ('Coffee Machine', 'Automatic coffee maker', 3);