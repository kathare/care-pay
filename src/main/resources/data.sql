insert into post (id, title, content,username,created_on) values
    (1, 'Welcome', 'Welcome to this assignment','test','2021-10-19 21:54:03.474000'),
    (2, 'Assignment', 'Implement the missing pieces','test','2021-10-19 21:54:03.474000'),
    (3, 'Task', 'Add support for comments on a post','test','2021-10-19 21:54:03.474000');
insert into comment (id, post_id, comment, username,created_on) values
    (4, 1, 'Kilroy was here','test','2021-10-19 21:54:03.474000'),
    (5, 1, 'Foobar too','test','2021-10-19 21:54:03.474000');
