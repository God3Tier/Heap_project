

export function Home(){
    return(
        <div className="homeBody">
            <h1>Home page</h1>
            <div className="topBody">
                <label>Background img here</label>
                <nav className="searchBar">
                    <label>Location:</label>
                    <select>
                        <option selected disabled>Select Location</option>
                        <option>Bencoolen MRT</option>
                    </select>
                    <label>Location:</label>
                    <select>
                        <option selected disabled>Select Location</option>
                        <option>Bencoolen MRT</option>
                    </select>
                    <label>Location:</label>
                    <select>
                        <option selected disabled>Select Location</option>
                        <option>Bencoolen MRT</option>
                    </select>
                    <label>Location:</label>
                    <select>
                        <option selected disabled>Select Location</option>
                        <option>Bencoolen MRT</option>
                    </select>
                    <button>Search</button>
                </nav>
            </div>
        </div>
    )
}