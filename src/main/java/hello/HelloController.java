package hello;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FriendList;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	private Facebook facebook;

	@Inject
	public HelloController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		try {
			if (!facebook.isAuthorized()) {
				return "redirect:/connect/facebook";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		List<FriendList> friends = facebook.friendOperations().getFriendLists();
		PagedList<Post> posts = facebook.feedOperations().getFeed();
		model.addAttribute(facebook.userOperations().getUserProfile());
		
		
		for(FriendList friend:friends){
			System.out.println("%%%%%%%%%%%%%%%%%%");
			System.out.println(friend.getName());
			System.out.println(friend.getId());
			System.out.println("%%%%%%%%%%%%%%%%%%%%");
		}
		
		for(Post post:posts){
			System.out.println("%%%%%%%%%%%%%%%%%%");
			System.out.println(post.getDescription());
			System.out.println(post.getId());
			System.out.println("%%%%%%%%%%%%%%%%%%%%");
		}
		
		User you = facebook.userOperations().getUserProfile();
		
		System.out.println(you.getBio());
		System.out.println(you.getId());
		System.out.println(you.getEmail());
		System.out.println(you.getHometown());
		System.out.println(you.getFirstName());
		System.out.println(you.getLastName());
		System.out.println(you.getRelationshipStatus());
		System.out.print(facebook.userOperations().getUserProfileImage()); //trebuie convertita in imagine
		System.out.println(facebook.userOperations().getUserProfile().getId());
		return "hello";
	}

}