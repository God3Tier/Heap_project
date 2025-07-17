import '../style/AboutUs.css'
import logo from '../assets/logo.png'

export function AboutUs(){
    return(
        <div className="aboutus-body">
            <div className="title">
                <div className="title-text">
                    <h1>Affordable Food Finder</h1>
                    <p>UP2U is dedicated to helping students discover budget-friendly meals across Singapore's vibrant food scene.</p>
                </div>
            </div>
            <div className="about">
                <div className="about-text">
                    <h3>Discovering Affordable Food: The Journey Behind UP2U's Creation</h3>
                    <p>UP2U was born from a desire to help students find budget-friendly meals without compromising on quality. Our founders, passionate about food and community, envisioned a platform that connects users with local food stalls that fit their budget. Since our launch, we've been dedicated to enhancing the dining experience for students in Singapore, making every meal an adventure.</p>
                </div>
            </div>
            <div className="team">
                <div className="team-text">
                    <h3>Together</h3>
                    <h2>Our Team</h2>
                    <label>Meet the passionate minds behind UP2U.</label>
                    <div className="row1">
                        <div className="person">
                            <img src={logo} alt="UP2U Logo"/>
                            <strong>Joseph Yau</strong>
                            <span>Backend Terrorist</span>
                        </div>
                        <div className="person">
                            <img src={logo} alt="UP2U Logo"/>
                            <strong>Clemira Jenkins</strong>
                            <span>Frontend Terrorist</span>
                        </div>
                        <div className="person">
                            <img src={logo} alt="UP2U Logo"/>
                            <strong>Orvin Filbert</strong>
                            <span>Pure Terrorist</span>
                        </div>
                        <div className="person">
                            <img src={logo} alt="UP2U Logo"/>
                            <strong>Yong Huey</strong>
                            <span>Burden</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}