# UP2U React Frontend (Cloning/Uploading and npm stuff)

1. When you plan to git clone, do remember to clone with the folder "up2u-frontend" <br>
2. ```cd up2u-react``` <br>
3. Once in the folder, run ```npm install``` (to install all node modules required) <br>
4. After installing, run ```npm run dev``` (to start the localhost to connect to) <br>
5. After creating/making changes to the files, only upload the files onto github. E.g. made ```home.jsx``` and ```Home.css```, only upload those files onto github into their respective files.
6. Commit changes on github should specify:
    - [Uploaded home.jsx, Description: Home page of UP2U] -> Uploaded into ```pages``` folder.
    - [Uploaded Home.css, Description: css for home.jsx] -> Uploaded into ```style``` folder.
    > [!NOTE]
    > If changes done to any of the files, do specify in the description when uploadeding the file.

# Pages, naming convention, formatting and location to place

1. All pages to create will be placed under ```pages``` folder <br>
2. The naming of the pages should be relevent to the page that you are building, e.g. ```about-us.jsx``` should be on the page for about us. <br>
3. When building a page, all page have a html formatting of ```<div></div>```, where you will be putting all you HTML code into. All you need to build is the body of the pages, where the naming convention will be ```<div className="<page-name>-body"></div>```. <br>
4. Refer to test.jsx to see how the formatting is like. <br>
5. Create each .css file for each page that you are working on, e.g. ```home.jsx``` should have a ```Home.css``` where all the css for ```home.jsx``` should be. Naming convention will be ```.<page-name>-body{}```
6. All css files other than ```App.css``` and ```index.css``` should be placed into ```style``` folder.
7. Refer to ```test.jsx``` and ```Test.css``` on how they should look like.

## TODO (Most important to least)

1. Build all necessary pages.
    - home.jsx (done, just need to prettify)
    - search.jsx (done, just need to link to backend for query and having it posted on webpage)
    - map.jsx (should be able to play into full map(react allows))
2. Linking to backend.
3. Implement APIs.
    - Map API
4. Prettify pages
5. Login/Signup page + security features 


<br>


# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
