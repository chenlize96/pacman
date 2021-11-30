'use strict';

var app;
var dynamicApp;
var image;
let intervalID = -1; //id of current interval
let frameInterval = -1; // id of an interval for each frame
let ghostRedImg = new Image();
let ghostPinkImg = new Image();
let ghostOrangeImg = new Image();
let ghostCyanImg = new Image();
let ghostVulBlueImg = new Image();
let ghostVulWhiteImg = new Image();
let ghostEyesImg = new Image();
var pacmanImg = new Image();
var dotImg = new Image();
var floorImg = new Image();
var wallImg = new Image();
var strawberryImg = new Image();
var appleImg = new Image();
var brownImg = new Image();
var woodImg = new Image();
var redImg = new Image();
var yellowImg = new Image();
var gateImg = new Image();

let pacmanDir;
let pacmanFrame;
let hScale;
let wScale;
let frameTime;
let pacmanLastLocX;
let pacmanLastLocY;
let pacmanVelX;
let pacmanVelY;
let framePerInterval = 20;


let ghostFrame;
let ghostCount;
let redLastLocX;
let redLastLocY;
let redVelX;
let redVelY;
let redDir;
let orangeLastLocX;
let orangeLastLocY;
let orangeVelX;
let orangeVelY;
let orangeDir;
let cyanLastLocX;
let cyanLastLocY;
let cyanVelX;
let cyanVelY;
let cyanDir;
let pinkLastLocX;
let pinkLastLocY;
let pinkVelX;
let pinkVelY;
let pinkDir;

let fruitLocX;
let fruitLocY;

let ghostNum = 4;
let fruitType = "strawberry";
let level = "easy";

/**
 * Create the pacman map app for a canvas, it won't be modified during each level.
 * @param canvas The canvas to draw map
 */
function createMap(canvas) {
    let c = canvas.getContext("2d");

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    let drawFullImage = function(img, dx, dy, dWidth, dHeight) {
        c.drawImage(img, dx, dy, dWidth, dHeight);
    }

    return {
        clear: clear,
        drawFullImage: drawFullImage,
        dims: {height: canvas.height, width: canvas.width}
    }
}

/**
 * Create the dynamic app for a canvas to render interacting items.
 * @param canvas The canvas to draw interacting items on
 */
function createDynamicMap(canvas) {
    let c = canvas.getContext("2d");

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    let drawFullImage = function(img, dx, dy, dWidth, dHeight) {
        c.drawImage(img, dx, dy, dWidth, dHeight);
    }

    let drawPartImage = function (img, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight) {
        c.drawImage(img, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
    }

    return {
        clear: clear,
        drawFullImage: drawFullImage,
        drawPartImage: drawPartImage,
        dims: {height: canvas.height, width: canvas.width}
    }
}

window.onload = function() {
    app = createMap(document.getElementById("map-canvas"));
    dynamicApp = createDynamicMap(document.getElementById("dynamic-canvas"));
    clear();
    imageInitialize();
    gameParamInit();
    //canvasDims();
    $("#fruit").change(function () {
        console.log($(this).val());
        fruitType = $(this).val();
    });
    $("#ghost").change(function () {
        console.log($(this).val());
        ghostNum = $(this).val();
    });
    //setTimeout(() => {canvasDims();} , 300);
    clearInterval(intervalID);
    clearInterval(frameInterval);

    $('#easy').click(getEasyLevel);
    $('#hard').click(getHardLevel);

    $('body').keydown(e => {
        //console.log("current direction is: " ,pacmanDir);
        //console.log(`key=${e.key},code=${e.code}`);
        //let keyPressed = e.key.toString();
        //console.log("key pressed is: ", keyPressed);
        if (e.key === "w" || e.key === "ArrowUp") {
            pacmanDir = 0;
            console.log(`key=${e.key},code=${e.code}`);
        }
        if (e.key === "d" || e.key === "ArrowRight") {
            pacmanDir = 1;
            console.log(`key=${e.key},code=${e.code}`);
        }
        if (e.key === "s" || e.key === "ArrowDown") {
            pacmanDir = 2;
            console.log(`key=${e.key},code=${e.code}`);
        }
        if (e.key === "a" || e.key === "ArrowLeft") {
            pacmanDir = 3;
            console.log(`key=${e.key},code=${e.code}`);
        }
       /** switch (keyPressed) {
            // up = 0
            case "w":
            case "ArrowUp":
                pacmanDir = 0;
                break;
            case "ArrowUp":
                pacmanDir = 0;
                break;
            // right = 1
            case "d":
            case "ArrowRight":
                pacmanDir = 1;
                break;
            case "ArrowRight":
                pacmanDir = 1;
                break;
            // down = 2
            case "s":
            case "ArrowDown":
                pacmanDir = 2;
                break;
            case "ArrowDown":
                pacmanDir = 2;
                break;
            // left = 3
            case "a":
            case "ArrowLeft":
                pacmanDir = 3;
                break;
            case "ArrowLeft":
                pacmanDir = 3;
                break;
            // undefined = do nothing
            default:
                break;
        }**/
        console.log("after direction is: " ,pacmanDir);
    });
    //intervalID = setInterval(updateWorld, 500);
};

/**
 * Update the world per interval.
 */
function updateWorld() {
    console.log(pacmanDir);
    clearInterval(intervalID);
    $.get("/update", {pacmanDirection: pacmanDir}, function(data) {
        console.log(data);
        clearInterval(frameInterval);
        frameInterval = setInterval(dynamicRender, 17, data);

    }, "json");
}

/**
 * Render the pacman.
 * @param pacman the pacman object.
 */
function pacmanRender(pacman) {
    // If at start moving state, refresh velocity for pacman.
    // //console.log("Current frameTime: ", frameTime);
    // console.log("pacmanLastLocX: ", pacmanLastLocX);
    // console.log("pacmanLastLocY: ", pacmanLastLocY);
    if (frameTime === framePerInterval) {
        pacmanVelX = ((pacman.loc.y * wScale) - pacmanLastLocX) / framePerInterval;
        pacmanVelY = ((pacman.loc.x * hScale) - pacmanLastLocY) / framePerInterval;
        // console.log("pacman.loc.x: ", pacman.loc.x * wScale);
        // console.log("pacman.loc.y: ", pacman.loc.y * hScale);
        // console.log("pacmanVelX: ", pacmanVelX);
        // console.log("pacmanVelY: ", pacmanVelY);
        // console.log("pacmanLastLocX: ", pacmanLastLocX);
        // console.log("pacmanLastLocY: ", pacmanLastLocY);
        //pacmanDir = pacman.currDir;
    }
    // Set up new render location.
    pacmanLastLocX = pacmanLastLocX + pacmanVelX;
    pacmanLastLocY = pacmanLastLocY + pacmanVelY;
    // Set up other parameters
    let imgSx = 16 * pacmanFrame;
    let imgSy = 16 * pacmanDir;

    // Draw the pacman
    dynamicApp.drawPartImage(pacmanImg, imgSx, imgSy, 16, 16,
        pacmanLastLocX, pacmanLastLocY, wScale, hScale);

    // Increment pacmanFrame
    if (frameTime%5 === 0) {
        if (pacmanFrame >= 3) {
            pacmanFrame = 0;
        } else {
            pacmanFrame ++;
        }
    }
    // Decrease frameTime
    if (frameTime < 1) {
        clearInterval(frameInterval);
        frameTime = framePerInterval;
        updateWorld();
        //intervalID = setInterval(updateWorld, 100);
    } else {
        frameTime --;
    }
}

/**
 * Render ghosts.
 * @param ghost the ghost object
 */
function ghostRender(ghost) {
    let imgSx = 0.0;
    let imgSy = 0.0;
    console.log(ghost);

    switch (ghost.name) {
        case "red":
            if (frameTime === framePerInterval) {
                redVelX = ((ghost.loc.y * wScale) - redLastLocX) / framePerInterval;
                redVelY = ((ghost.loc.x * hScale) - redLastLocY) / framePerInterval;
                redDir = ghost.currDir;
            }

            // Set up new render location.
            redLastLocX = redLastLocX + redVelX;
            redLastLocY = redLastLocY + redVelY;
            // Set up other parameters
            imgSx = 16 * ghostFrame;
            imgSy = 16 * ghost.currDir;
            // Draw the red ghost
            if(ghost.ghostStatus === "vulnerable_dark_blue") {
                dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                    redLastLocX, redLastLocY, wScale, hScale);
            }
            else if(ghost.ghostStatus === "vulnerable_blink"){
                if(ghostFrame === 1) {
                    dynamicApp.drawPartImage(ghostVulWhiteImg, imgSx, imgSy, 16, 16,
                        redLastLocX, redLastLocY, wScale, hScale);
                }
                else {
                    dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                        redLastLocX, redLastLocY, wScale, hScale);
                }
            }
            else if(ghost.ghostStatus === "dead"){
                /*dynamicApp.drawPartImage(ghostEyesImg, imgSx, imgSy, 16, 16,
                    redLastLocX, redLastLocY, wScale, hScale);*/
                dynamicApp.drawPartImage(ghostEyesImg, 0, imgSy, 16, 16,
                    redLastLocX, redLastLocY, wScale, hScale);
            }
            else{
                dynamicApp.drawPartImage(ghostRedImg, imgSx, imgSy, 16, 16,
                    redLastLocX, redLastLocY, wScale, hScale);
            }
            break;

        case "pink":
            if (frameTime === framePerInterval) {
                pinkVelX = ((ghost.loc.y * wScale) - pinkLastLocX) / framePerInterval;
                pinkVelY = ((ghost.loc.x * hScale) - pinkLastLocY) / framePerInterval;
                pinkDir = ghost.currDir;
            }
            // Set up new render location.
            pinkLastLocX = pinkLastLocX + pinkVelX;
            pinkLastLocY = pinkLastLocY + pinkVelY;
            // Set up other parameters
            imgSx = 16 * ghostFrame;
            imgSy = 16 * ghost.currDir;

            // Draw the red ghost
            if(ghost.ghostStatus === "vulnerable_dark_blue") {
                dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                    pinkLastLocX, pinkLastLocY, wScale, hScale);
            }
            else if(ghost.ghostStatus === "vulnerable_blink"){
                if(ghostFrame === 1) {
                    dynamicApp.drawPartImage(ghostVulWhiteImg, imgSx, imgSy, 16, 16,
                        pinkLastLocX, pinkLastLocY, wScale, hScale);
                }
                else {
                    dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                        pinkLastLocX, pinkLastLocY, wScale, hScale);
                }
            }
            else if(ghost.ghostStatus === "dead"){
                /*dynamicApp.drawPartImage(ghostEyesImg, imgSx, imgSy, 16, 16,
                    pinkLastLocX, pinkLastLocY, wScale, hScale);*/
                dynamicApp.drawPartImage(ghostEyesImg, 0, imgSy, 16, 16,
                    pinkLastLocX, pinkLastLocY, wScale, hScale);
            }
            else {
                dynamicApp.drawPartImage(ghostPinkImg, imgSx, imgSy, 16, 16,
                    pinkLastLocX, pinkLastLocY, wScale, hScale);
            }
            break;

        case "orange":
            if (frameTime === framePerInterval) {
                orangeVelX = ((ghost.loc.y * wScale) - orangeLastLocX) / framePerInterval;
                orangeVelY = ((ghost.loc.x * hScale) - orangeLastLocY) / framePerInterval;
                orangeDir = ghost.currDir;
            }
            // Set up new render location.
            orangeLastLocX = orangeLastLocX + orangeVelX;
            orangeLastLocY = orangeLastLocY + orangeVelY;
            // Set up other parameters
            imgSx = 16 * ghostFrame;
            imgSy = 16 * ghost.currDir;

            // Draw the red ghost
            if(ghost.ghostStatus === "vulnerable_dark_blue") {
                dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                    orangeLastLocX, orangeLastLocY, wScale, hScale);
            }
            else if(ghost.ghostStatus === "vulnerable_blink"){
                if(ghostFrame === 1) {
                    dynamicApp.drawPartImage(ghostVulWhiteImg, imgSx, imgSy, 16, 16,
                        orangeLastLocX, orangeLastLocY, wScale, hScale);
                }
                else {
                    dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                        orangeLastLocX, orangeLastLocY, wScale, hScale);
                }
            }
            else if(ghost.ghostStatus === "dead"){
                /*dynamicApp.drawPartImage(ghostEyesImg, imgSx, imgSy, 16, 16,
                    orangeLastLocX, orangeLastLocY, wScale, hScale);*/
                dynamicApp.drawPartImage(ghostEyesImg, 0, imgSy, 16, 16,
                    orangeLastLocX, orangeLastLocY, wScale, hScale);
            }
            else {
                dynamicApp.drawPartImage(ghostOrangeImg, imgSx, imgSy, 16, 16,
                    orangeLastLocX, orangeLastLocY, wScale, hScale);
            }

            break;

        case "cyan":
            if (frameTime === framePerInterval) {
                cyanVelX = ((ghost.loc.y * wScale) - cyanLastLocX) / framePerInterval;
                cyanVelY = ((ghost.loc.x * hScale) - cyanLastLocY) / framePerInterval;
                cyanDir = ghost.currDir;
            }
            // Set up new render location.
            cyanLastLocX = cyanLastLocX + cyanVelX;
            cyanLastLocY = cyanLastLocY + cyanVelY;
            // Set up other parameters
            imgSx = 16 * ghostFrame;
            imgSy = 16 * ghost.currDir;

            // Draw the red ghost
            if(ghost.ghostStatus === "vulnerable_dark_blue") {
                dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                    cyanLastLocX, cyanLastLocY, wScale, hScale);
            }
            else if(ghost.ghostStatus === "vulnerable_blink"){
                if(ghostFrame === 1) {
                    dynamicApp.drawPartImage(ghostVulWhiteImg, imgSx, imgSy, 16, 16,
                        cyanLastLocX, cyanLastLocY, wScale, hScale);
                }
                else {
                    dynamicApp.drawPartImage(ghostVulBlueImg, imgSx, imgSy, 16, 16,
                        cyanLastLocX, cyanLastLocY, wScale, hScale);
                }
            }
            else if(ghost.ghostStatus === "dead"){
                /*dynamicApp.drawPartImage(ghostEyesImg, imgSx, imgSy, 16, 16,
                    orangeLastLocX, orangeLastLocY, wScale, hScale);*/
                dynamicApp.drawPartImage(ghostEyesImg, 0, imgSy, 16, 16,
                    cyanLastLocX, cyanLastLocY, wScale, hScale);
            }
            else {
                dynamicApp.drawPartImage(ghostCyanImg, imgSx, imgSy, 16, 16,
                    cyanLastLocX, cyanLastLocY, wScale, hScale);
            }
            break;

        default:
            console.log("Wrong type of ghost, check data!");
            break;
    }


    // Set up the counter for ghosts
    ghostCount ++;
    if (ghostCount >= 3) {
        if (ghostFrame > 0) {
            ghostFrame = 0;
        } else {
            ghostFrame++;
        }
        ghostCount = 0;
    }
}


/**
 * Render dynamic items on canvas, this happens per frame interval.
 */
function dynamicRender(data) {
    dynamicApp.clear();
    console.log(data);
    let dynamics = data.dynamics;
    let eatenDot = data.eaten;
    let score = data.score;
    let eatenAll = data.eatenAll;
    dynamics.forEach(function(obj) {
        obj = obj.listener;
        //console.log(obj)
        if(!obj) {
            return;
        }
        switch (obj.type) {
            case "pacman":
                //console.log("(" + obj.loc.x + ", " + obj.loc.y + ")");
                pacmanRender(obj);
                break;
            case "ghost":
                ghostRender(obj);
                break;
            default:
                console.log("Invalid sub object to process in front end.");
                break;
        }
    });

    eatenDot.forEach(function(obj) {
        let locX = obj.loc.y * wScale;
        let locY = obj.loc.x * hScale;
        /*if(obj.type == "dot") {
            dotNum++;
        }*/
        app.drawFullImage(floorImg, locX, locY, wScale, hScale);
    });

    if(data.fruitAppear) {
       if(fruitType == "strawberry"){
           app.drawFullImage(strawberryImg, fruitLocX, fruitLocY, wScale, hScale);
       }
       else if (fruitType == "apple"){
           app.drawFullImage(appleImg, fruitLocX, fruitLocY, wScale, hScale);
       }
    }
    else{
        app.drawFullImage(floorImg, fruitLocX, fruitLocY, wScale, hScale);
    }
    if(data.lives !== 3) {
        if(data.lives === 2) {
            $("#life_3").hide();
        }
        else if(data.lives === 1) {
            $("#life_2").hide();
        }
        else if(data.lives === 0) {
            $("#life_1").hide();
            alert("Game Over!");
            clear();
        }
    }
    let scoreText = document.getElementById("score_text");
    scoreText.innerText = "Score: " + score;

    if(eatenAll){
        if(level=="easy"){
            alert("You finished easy level, now entering hard level!");
            clear();
            getHardLevel();
        }
        else{
            alert("You finished hard level, now starting new game!");
            clear();
            getEasyLevel()
        }
        //intervalID = setInterval(updateWorld, 500);
    }
}

/**
 * Initialize game parameters.
 */
function gameParamInit() {
    pacmanDir = 1; // default dir should be the same as the image. right is 1
    pacmanFrame = 0;
    ghostFrame = 0;
    ghostCount = 0;
    redDir = 1;
    orangeDir = 1;
    cyanDir = 1;
    pinkDir = 1;
    frameTime = frameInterval;
}

/**
 * Initialize all images object.
 */
function imageInitialize() {
    wallImg.src = "wall.png";
    floorImg.src = "floor.png";
    dotImg.src = "pellet.png";
    pacmanImg.src = "pacman.png";
    strawberryImg.src = "strawberry.png";
    appleImg.src = "apple.png";
    ghostRedImg.src = "ghost_red.png";
    ghostPinkImg.src = "ghost_pink.png";
    ghostOrangeImg.src = "ghost_orange.png";
    ghostCyanImg.src = "ghost_cyan.png";
    brownImg.src = "brown.png";
    woodImg.src = "wood.png";
    redImg.src = "red.png";
    yellowImg.src = "yellow.png";
    ghostVulBlueImg.src = "ghost_vul_blue.png";
    ghostVulWhiteImg.src = "ghost_vul_white.png";
    ghostEyesImg.src = "ghost_eyes.png";
    gateImg.src = "gate.png";
}

/**
 * Render the map based on passing grid data.
 * @param map2DArray The map 2d array passed from the backend
 */
function mapRender(map2DArray) {
    if (!map2DArray) {
        console.log("Map data is invalid, render failed.");
        return;
    }

    let mapH = map2DArray.length;
    let mapW = map2DArray[0].length;
    hScale = app.dims.height / mapH;
    wScale = app.dims.width / mapW;

    // console.log("map H is: ", mapH);
    // console.log("map W is: ", mapW);

    //return -1;
    // Iterate through all map point
    for (let w = 0; w < mapW; w++) {
        for (let h = 0; h < mapH; h++) {
            let locX = w * wScale;
            let locY = h * hScale;
            // Draw floor
            app.drawFullImage(floorImg, locX, locY, wScale, hScale);
            if (map2DArray[h][w] === 'W') {
                // Draw wall
                app.drawFullImage(wallImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'D') {
                // Draw small dot
                app.drawFullImage(dotImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'L') {
                // Draw large dot
                app.drawFullImage(dotImg, locX - wScale / 2, locY - hScale / 2,
                    wScale * 2, hScale * 2);
            } else if (map2DArray[h][w] === 'S') {
                fruitLocX = locX;
                fruitLocY = locY;
                //app.drawFullImage(fruitImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'B') {
                app.drawFullImage(brownImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'b') {
                app.drawFullImage(woodImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'R') {
                app.drawFullImage(redImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'G') {
                app.drawFullImage(gateImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'E') {
                //console.log("Empty index, render Nothing...");
            } else if (map2DArray[h][w] === 'P') {
                // Draw Pacman
                pacmanLastLocX = locX;
                pacmanLastLocY = locY;
                pacmanVelX = 0;
                pacmanVelY = 0;
                // console.log("Initial pacmanLastLocX: ", pacmanLastLocX);
                // console.log("Initial pacmanLastLocY: ", pacmanLastLocY);
                dynamicApp.drawPartImage(pacmanImg, 16, 16, 16, 16,
                    locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'T') {
                app.drawFullImage(yellowImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === '1') {
                redLastLocX = locX;
                redLastLocY = locY;
                redVelX = 0;
                redVelY = 0;
                dynamicApp.drawPartImage(ghostRedImg, 16, 16, 16, 16,
                    locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === '2') {
                pinkLastLocX = locX;
                pinkLastLocY = locY;
                pinkVelX = 0;
                pinkVelY = 0;
                dynamicApp.drawPartImage(ghostPinkImg, 16, 16, 16, 16,
                    locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === '3') {
                orangeLastLocX = locX;
                orangeLastLocY = locY;
                orangeVelX = 0;
                orangeVelY = 0;
                dynamicApp.drawPartImage(ghostOrangeImg, 16, 16, 16, 16,
                    locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === '4') {
                cyanLastLocX = locX;
                cyanLastLocY = locY;
                cyanVelX = 0;
                cyanVelY = 0;
                dynamicApp.drawPartImage(ghostCyanImg, 16, 16, 16, 16,
                    locX, locY, wScale, hScale);
            } else {
                // Invalid index
                console.log("Current index of Map is undefined.");
                console.log("Index is: w =", w,", h =", h, " , val =", map2DArray[h][w]);
            }
        }
    }
}

/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width, fruitType:$("#fruit").val(), ghostNum:$("#ghost").val()}, function (data) {
        console.log(data);
        mapRender(data);
    }, "json");
}

/**
 * Set the easy level
 */
function getEasyLevel() {
    //location.reload();
    clear();
    level = "easy";
    canvasDims();
    //canvasDims();
    clearInterval(intervalID);
    clearInterval(frameInterval);
    intervalID = -1;
    frameInterval = -1;
    intervalID = setInterval(updateWorld, 500);
}

/**
 * Set the hard level
 */
function getHardLevel() {
    $.post("/level", {level: "hard",fruitType:$("#fruit").val(), ghostNum:$("#ghost").val()}, function (data) {
        console.log(data);
        clear();
        mapRender(data);
        clearInterval(intervalID);
        clearInterval(frameInterval);
        intervalID = -1;
        frameInterval = -1;
        intervalID = setInterval(updateWorld, 500);
        level = "hard";
    }, "json");
}

/**
 * Clear the ghosts and pacman
 */
function clear() {
    $.get("/clear");
    /*clearInterval(intervalID);
    intervalID = -1;
    setTimeout(() => { app.clear(); }, 100);*/
    clearInterval(intervalID);
    intervalID = -1;
    clearInterval(frameInterval);
    frameInterval = -1;
    //app.clear();
    dynamicApp.clear();
}
