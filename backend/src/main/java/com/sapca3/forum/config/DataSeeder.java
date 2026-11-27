package com.sapca3.forum.config;

import com.sapca3.forum.model.*;
import com.sapca3.forum.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ThreadRepository threadRepository;
    private final PostRepository postRepository;

    public DataSeeder(UserRepository userRepository,
                      CategoryRepository categoryRepository,
                      ThreadRepository threadRepository,
                      PostRepository postRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.threadRepository = threadRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args){
        if(userRepository.count() > 0){
            return;//already seeded
        }

        /*
         *USERS
         * - insecure branch will keep plaintext, secure branch will hash them
         */
        User member = new User();
        member.setUsername("member1");
        member.setEmail("member1@example.com");
        member.setPassword("Member123!");
        member.setRole(Role.MEMBER);
        member.setGamertag("FragMaster");
        member.setDiscordHandle("fragmaster#1234");
        member.setBio("FPS player, mostly Valorant and CS2.");
        userRepository.save(member);

        User moderator = new User();
        moderator.setUsername("mod1");
        moderator.setEmail("mod1@example.com");
        moderator.setPassword("Moderator123!");
        moderator.setRole(Role.MODERATOR);
        moderator.setGamertag("ModSquad");
        moderator.setDiscordHandle("modsquad#0001");
        moderator.setBio("Forum moderator. Be nice.");
        userRepository.save(moderator);

        // CATEGORIES
        Category fps = categoryRepository.save(new Category("FPS", "First-person shooters"));
        Category rpg = categoryRepository.save(new Category("RPG", "Role-playing games"));
        Category indie = categoryRepository.save(new Category("Indie", "Independent games"));
        Category lfg = categoryRepository.save(new Category("LFG", "Looking for group"));

        // THREADS
        ForumThread t1 = new ForumThread();
        t1.setCategory(fps);
        t1.setAuthor(member);
        t1.setTitle("Best crosshair settings?");
        t1.setBody("What crosshair settings do you all use? Mine keeps feeling off.");
        threadRepository.save(t1);

        ForumThread t2 = new ForumThread();
        t2.setCategory(rpg);
        t2.setAuthor(member);
        t2.setTitle("Favourite RPG of all time");
        t2.setBody("Mine has to be The Witcher 3. What's yours?");
        threadRepository.save(t2);

        ForumThread t3 = new ForumThread();
        t3.setCategory(indie);
        t3.setAuthor(moderator);
        t3.setTitle("Hidden indie gems");
        t3.setBody("Post indie games that deserve more attention.");
        threadRepository.save(t3);

        ForumThread t4 = new ForumThread();
        t4.setCategory(lfg);
        t4.setAuthor(member);
        t4.setTitle("LFG: Ranked duo, EU, Gold+");
        t4.setBody("Looking for a chill duo for ranked in the evenings. Add me on Discord.");
        threadRepository.save(t4);

        // REPLIES
        Post p1 = new Post();
        p1.setThread(t1);
        p1.setAuthor(moderator);
        p1.setBody("Try a small static dot with no outline. Works well for most people.");
        postRepository.save(p1);

        Post p2 = new Post();
        p2.setThread(t2);
        p2.setAuthor(moderator);
        p2.setBody("Hard to beat Skyrim for me.");
        postRepository.save(p2);

    }
}