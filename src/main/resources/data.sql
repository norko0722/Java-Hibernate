-- Insert sample users
INSERT INTO users (first_name, last_name, email, password, role) VALUES
('John', 'Doe', 'john.doe@example.com', 'password123', 'ADMIN'),
('Jane', 'Smith', 'jane.smith@example.com', 'password123', 'USER'),
('Bob', 'Johnson', 'bob.johnson@example.com', 'password123', 'USER');

-- Insert sample equipment
INSERT INTO equipment (name, description, is_available) VALUES
('Projector', 'HD Projector with HDMI input', 1),
('Whiteboard', 'Large whiteboard with markers', 1),
('Video Conference System', '4K camera with microphone array', 1),
('Coffee Machine', 'Automatic coffee maker', 1);

-- Insert sample meeting rooms
INSERT INTO meeting_rooms (name, capacity, location, is_active) VALUES
('Conference Room A', 10, 'First Floor', 1),
('Conference Room B', 20, 'Second Floor', 1),
('Board Room', 15, 'Third Floor', 1),
('Training Room', 30, 'First Floor', 1);

-- Link equipment to rooms
INSERT INTO room_equipment (room_id, equipment_id) VALUES
(1, 1), -- Conference Room A has Projector
(1, 2), -- Conference Room A has Whiteboard
(2, 1), -- Conference Room B has Projector
(2, 3), -- Conference Room B has Video Conference System
(3, 1), -- Board Room has Projector
(3, 2), -- Board Room has Whiteboard
(3, 3), -- Board Room has Video Conference System
(4, 1), -- Training Room has Projector
(4, 2); -- Training Room has Whiteboard 