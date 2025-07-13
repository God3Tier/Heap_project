import { NavBar } from "./NavBar";
import { Outlet } from 'react-router-dom'
import logo from "../assets/logo.png"

export function Layout(){
    return(
        <>
            {/* Header */}
            <div className="header">
                <NavBar/>
            </div>

            {/* Mid */}
            <main className="mid">
            <div className="content-wrapper">
          <Outlet />
             </div>
            </main>

            {/* Footer */}
            <div className="footer">
                <div className="footer-sub">
                    <div className="sub-to-update">
                        <label>Subscribe to Updates</label><br/>
                        <label>Stay informed about the latest food deals and offers!</label>
                    </div>
                    <div className="sub-field">
                        <form>
                            <input type="text" id="email" name="email" placeholder="Your Email Here"/>
                            <button>Subscribe</button>
                        </form>
                    </div>
                </div>
                <div className="footer-links">
                    <div className="logo">
                        <img src={logo} alt="UP2U Logo"/>
                    </div>
                    <div className="helpful-links">
                        <ul>
                            <label>Helpful Links</label>
                            <li><a href="#/about-us">About Us</a></li>
                            <li><a href="#/contact-support">Contact Support</a></li>
                            <li><a href="/faq.html">FAQs</a></li>
                            <li><a href="/blog.html">Blog Post</a></li>
                            <li><a href="/careers.html">Careers</a></li>
                        </ul>
                    </div>
                    <div class="social-media">
                        <ul>
                            <label>Social Media</label>
                            <li><a href="./about-us.html">Facebook Page</a></li>
                            <li><a href="./contact-support.html">Instagram Profile</a></li>
                            <li><a href="./faq.html">Twitter Feed</a></li>
                            <li><a href="./blog.html">LinkedIn Page</a></li>
                            <li><a href="./careers.html">YouTube Channel</a></li>
                        </ul>
                    </div>
                    <div class="resources">
                        <ul>
                            <label>Resources</label>
                            <li><a href="./about-us.html">User Guide</a></li>
                            <li><a href="./contact-support.html">App Feature</a></li>
                            <li><a href="./faq.html">Community Forum</a></li>
                            <li><a href="./blog.html">Feedback</a></li>
                            <li><a href="./careers.html">Support Center</a></li>
                        </ul>
                    </div>
                    <div class="legal">
                        <ul>
                            <label>Legal</label>
                            <li><a href="./about-us.html">Privacy Policy</a></li>
                            <li><a href="./contact-support.html">Terms of Use</a></li>
                            <li><a href="./faq.html">Cookie Policy</a></li>
                            <li><a href="./blog.html">Company Info</a></li>
                            <li><a href="./careers.html">Our Story</a></li>
                        </ul>
                    </div>
                    <div class="contact-us">
                        <ul>
                            <label>Contact Us</label>
                            <li><a href="./about-us.html">Get in Touch</a></li>
                            <li><a href="./careers.html">Join Our Team</a></li>
                            <li><a href="./faq.html">Explore Our Blog</a></li>
                            <li><a href="./blog.html">Sign Up Now</a></li>
                            <li><a href="./careers.html">Read Our Reviews</a></li>
                        </ul>
                    </div>
                </div>
                <div className="credits">
                    <hr></hr>
                    
                    <div className="creds">
                        <p>&copy; 2025 UP2U. All rights reserved.</p>
                        <a href="./about-us.html">Privacy Policy</a>
                        <a href="./about-us.html">Terms of Service</a>
                        <a href="./about-us.html">Cookie Settings</a>
                    </div>
                </div>
            </div>
        </>
    )
}