# Rules - GitHub Process {#githubRules}

[Back to home](@ref mainpage)

### To create a new issue:
1. Make sure that the same bug or feature is not already mentioned in another issue.
2. Create an issue and choose the most appropriate type, **write a detailed explanation** and add relevant tags (labels).
3. Link the issue to the correct project.
4. Describe the issue clearly (Description should be clear for one who doesn't know anything about the issue before).
5. Add (How to test) to the description illustrating tests needed to verify that the issue is solved.
6. Mention the potential assignees of the issue, don't assign yet.

### To estimate an issue:
1. Pick one issue from **Estimation** list. 
2. Read the issue and make sure you understand it correctly. Also make sure it has a very clear outcome (a clear definition of **done**).
3. Ask questions to the author if the issue needs more clarifications.
4. Think how long the issue should take for a complete newcomer with minimal knowledge and experience.
5. Then, double that estimated time. For example, if a problem requires 4 hours,
we say it should require at most 8 hours. This is to account for issues that could arise while working on the issue. 
6. Write your estimation as a comment. Examples: `2h`, `3d`, `1w`. (1w = 5d, 1d = 8h)
7. Once enough estimations are gathered (with low deviation). We (usually a senior, mostly Eng. Aly Sakr) pick the maximum reasonable estimation,
we add a duration tag to the issue, and the issue moves to "Ready". 

### To plan a sprint:
1. Check all the estimated issues in "Ready" list.
2. Choose the issues that we wish to finish in the next sprint.
3. Add issues to "Sprint" list until the total is around the sprint capacity.
4. Everyone on the team must agree on the sprint plan (after this point of time, estimations cannot change).

### To start working on an issue:
1. Open the project view and pick an unassigned issue from "Sprint" list.
2. Read the issue details and make sure you understand the task correctly. If the details of the issue seem sparse,
try to mention or contact the author of the issue for clarification. If the author is not available, you can contact Eng. Aly Sakr for help.  
3. Assign yourself. This is so no 2 people accidently work on the same task.
4. Create a new feature branch - Look [Naming Conventions](#naming-conventions) below - from the main branch and start working on the issue.

### To finalize your design:
1. If you made any changes to the code, commit all your changes to your feature branch and push it.
2. Create a pull-request to merge into the development branch.
3. Link the pull-request to the issue.
4. Request reviews from potential reviewers.
5. Before closing the issue Developer should write the story of the issue and the final result in a comment.

### To finalize working on an issue:
1. Commit all your changes to your feature branch and push it.
2. Commit message should be descriptive like the following example: 
````
backend/docs/1090/new_api_dev_docs
- Added document and images explaining how to contribute to new API design.
- Fixed minor doxygen errors.
````
3. If a pull-request is not already created, create a pull-request to merge into the development branch.
4. Link the pull-request to the issue.
5. Request reviews from potential reviewers.

### To review and test a request:
1. Navigate to the linked pull-request.
2. Pull the feature branch and run a manual test, think of different conditions that may not be correctly handled.
3. Check the file changes in the pull-request and write your review (preferably on the relevant changes), make sure that the changes are relevant to the issue, remove all other unnecessary changes.
4. Make sure that the necessary documentation and logging are implemented, and that test coverage is good.
5. If tests are successful and the review has no critical points, then approve the pull-request. If not, then request changes.
6. Reviewer should write a test report before merging the PR.
7. Reviewer should give a feedback on quality of code, testing, logging and documentation.
8.  **Don't merge** if the commit message is not descriptive.

### To handle requested changes:
1. Navigate to the pull-request.
2. Check all the comments and address them (either by committing or commenting).
3. Commit your changes and push the feature branch.
4. Re-request a review from the same reviewer.

### To handle an approved review:
1. Navigate to the pull-request.
2. Make sure no build or debug files are uploaded, only code files that are changed.
3. Merge the feature branch into the development branch.
4. Delete the feature branch.

### To handle merge conflicts:
1. Merge the development branch into the feature branch.
2. Handle additive and false merge conflicts by yourself.
3. For real merge conflicts, check history to find the original author.
4. Handle real merge conflicts with the original author.
5. For real merge conflicts, write the file name and the first line number in the commit description.

### Additional rules:
1. After doing any relevant activity on an issue (implementation, review, just some help, etc.),
**you must specify how much time you spent on the issue (write a comment) and in our planning sheet**. 
2. Every week merge the development branch into all feature branches (reduces merge conflicts).
3. Reverts must always say "revert" in the summary.
4. Never revert a merge commit.
5. Never rebase a pushed commit.
6. A commit comment title must always start with the branch name.
7. Don't include any (.gitignore) files. To ignore files/directories, add their absolute path in the main (.gitignore) file in the root directory.
8. **If you faced a problem first thing to do is to look it up in the FAQ.** If a solution doesn't exist there,
you are free to ask anyone on the team for help.
9. **If a problem is not found in the FAQ, then it must be added to the FAQ.**
10. Please remember to:
       1- Delete branch after merging
       2- Add tags in issues and PRs
       3- Link PRs to ADT-Website Github Project
       4- Link issues to PRs

### Naming conventions:

IMPORTANT: ALWAYS USE LOWERCASE CHARACTERS WHEN NAMING YOUR BRANCH.

THIS CAN CAUSE CONFUSION BETWEEN GIT AND INTELLIJ.
INTELLIJ WILL FETCH A DIFFERENT BRANCH IF THERE ARE 2 BRANCHES ON ORIGIN WITH DIFFERENT CASES!

Ex. If there's a branch named "Backend/feat/754/..." that was created on GITHUB then fetched to intellij then commited on it then push these commits, intellij will create a new branch called "backend/feat/754/..." and push the changes there!

Branch name: `<scope>/<type>/<issueNr>/<subject>`

Pull-request name: `<scope>-<type>-<issueNr>-<subject>`

Where:
- `<scope>` is the module or the subproject you are working on.
  - **CI**: for issues related to docker, jenkins, github actions, github, cloud, etc.
  - **core**: for issues related to the core modules.
  - **backend**:  for issues related to the backend modules.
  - **frontend**: for issues related to the frontend modules and GUI.
  - **docs**: for issues related to general documentation, UML diagrams, design, FAQ, etc.
  - **tools**: for issues related to the building tools like gradle, CMake, Qt, formatting and style.
  - **integration**: for issues related to integrating different modules.
  - **web**: for issues related to web development.
  - **general**: for general issues that doesn't fall under a certain scope
- `<type>` type of the change that you are making:
  - **feat**: A new feature.
  - **fix**: A bug fix.
  - **docs**: Documentation only changes.
  - **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, newline, line endings, etc).
  - **refactor**: A code change that neither fixes a bug or adds a feature.
  - **test**: Related to testing.
- `<issueNr>` is the GitHub number assigned to an issue.
- `<subject>` is the title of the issue.

**Notes**: 
  - It is **backend** NOT **back-end** or **backEnd**.
  - In case of general issues, if you can think of a new proper scope, don't hesitate to add it here and add a new issue template for it.
  - Adding more documentation for backend modules is: `backend/docs/...`. However, updating/adding general documentations like this file or updating the UML diagram, design, FAQ, etc is: `docs/feat/...`

Example branch: `core/feat/20/LUTHandling`

Example pull-request: `core-feat-20-LUTHandling`

[Back to home](@ref mainpage)
