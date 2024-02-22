const nameInput = document.querySelector(".search-input");
let resultBox = document.querySelector(".result-box");
const mainEl = document.querySelector("main");

nameInput.addEventListener("keydown", (event) => {
    if(event.keyCode === 13 && nameInput.value !== '') {

        new Promise((resolve, reject) => {
            resolve(getData(`https://api.github.com/users/${nameInput.value}`));
        }).then(data => {
            if(data.id !== undefined) {
                renderResult(data);
            } else {
                userNotFound();
            }
        })
    }
});


function renderResult(data) {
    clearResult();

    const profileBox = document.createElement("div");
    const avatarBox = document.createElement("div");
    const profileContentsBox = document.createElement("div");
    const gitInfoBox = document.createElement("div");
    const personalBox = document.createElement("div");
    const avatar = document.createElement("img");
    const viewProfile = document.createElement("button");
    const repoCount = document.createElement("span");
    const gistCount = document.createElement("span");
    const followerCount = document.createElement("span");
    const followingCount = document.createElement("span");
    const company = document.createElement("p");
    const website = document.createElement("p");
    const location = document.createElement("p");
    const signUpDate = document.createElement("p");

    profileBox.classList.add("profile-box");
    avatarBox.classList.add("avatar-box");
    profileContentsBox.classList.add("profile-contents-box");
    gitInfoBox.classList.add("gitInfo-box");
    personalBox.classList.add("personal-box");
    avatar.setAttribute("src", data.avatar_url);
    avatar.setAttribute("alt", "avatar image");
    avatar.classList.add("avatar");
    viewProfile.setAttribute("href", data.html_url);
    repoCount.textContent = `Public repos: ${data.public_repos}`;
    gistCount.textContent = `Public gists: ${data.public_gists}`;
    followerCount.textContent = `Followers: ${data.followers}`;
    followingCount.textContent = `Following: ${data.following}`;
    company.textContent = `Company: ${data.company}`;
    website.textContent = `Website: ${data.blog}`;
    location.textContent = `Location: ${data.location}`;
    signUpDate.textContent = `Created at: ${data.created_at}`;

    profileBox.append(avatarBox, profileContentsBox);
    avatarBox.append(avatar, viewProfile);
    profileContentsBox.append(gitInfoBox, personalBox);
    gitInfoBox.append(repoCount, gistCount, followerCount, followingCount);
    personalBox.append(company, website, location, signUpDate);
    resultBox.append(profileBox);

    const reposBox = document.createElement("div");
    const reposTitle = document.createElement("span");
    reposBox.classList.add("repos-box");
    reposTitle.classList.add("repos-title");
    reposTitle.textContent = "Latest Repos";
    reposBox.append(reposTitle);

    new Promise((resolve, reject) => {
        resolve(getData(data.repos_url));
    }).then(data => {

        data.forEach((repo) => {
            const repoName = repo.name;
            const star = repo.stargazers_count;
            const watcher = repo.watchers_count;
            const fork = repo.forks_count;

            const repoEl = document.createElement("div");
            const repoTitle = document.createElement("span");
            const repoInfo = document.createElement("div");
            const starEl = document.createElement("span");
            const watcherEl = document.createElement("span");
            const forkEl = document.createElement("span");

            repoEl.classList.add("repo");
            repoTitle.classList.add("repo-title");
            repoInfo.classList.add("repo-info");
            repoTitle.textContent = repoName;
            starEl.textContent = `Stars: ${star}`;
            watcherEl.textContent = `Watchers: ${watcher}`;
            forkEl.textContent = `Forks: ${fork}`;

            repoEl.append(repoTitle, repoInfo);
            repoInfo.append(starEl, watcherEl, forkEl);
            reposBox.append(repoEl);
        });
        
        resultBox.append(reposBox);
    });
}

function clearResult() {
    resultBox.remove();
    resultBox = document.createElement("div");
    resultBox.classList.add("result-box");
    mainEl.append(resultBox);
}

function userNotFound() {
    clearResult();
    resultBox.textContent = "User does not exist";
}

async function getData(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;

    } catch (error) {
        console.error(error);
    }
} 