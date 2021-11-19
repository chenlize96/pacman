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
var pacmanImg = new Image();
var dotImg = new Image();
var floorImg = new Image();
var wallImg = new Image();
var fruitImg = new Image();
var brownImg = new Image();
var woodImg = new Image();
var redImg = new Image();

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
    setTimeout(() => {canvasDims();} , 300);
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
    intervalID = setInterval(updateWorld, 500);
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
            dynamicApp.drawPartImage(ghostRedImg, imgSx, imgSy, 16, 16,
                redLastLocX, redLastLocY, wScale, hScale);
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
            dynamicApp.drawPartImage(ghostPinkImg, imgSx, imgSy, 16, 16,
                pinkLastLocX, pinkLastLocY, wScale, hScale);
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
            dynamicApp.drawPartImage(ghostOrangeImg, imgSx, imgSy, 16, 16,
                orangeLastLocX, orangeLastLocY, wScale, hScale);
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
            dynamicApp.drawPartImage(ghostCyanImg, imgSx, imgSy, 16, 16,
                cyanLastLocX, cyanLastLocY, wScale, hScale);
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
    data.forEach(function(obj) {
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
    fruitImg.src = "strawberry.png";
    ghostRedImg.src = "ghost_red.png";
    ghostPinkImg.src = "ghost_pink.png";
    ghostOrangeImg.src = "ghost_orange.png";
    ghostCyanImg.src = "ghost_cyan.png";
    brownImg.src = "brown.png";
    woodImg.src = "wood.png";
    redImg.src = "red.png";
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
                app.drawFullImage(fruitImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'B') {
                app.drawFullImage(brownImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'b') {
                app.drawFullImage(woodImg, locX, locY, wScale, hScale);
            } else if (map2DArray[h][w] === 'R') {
                app.drawFullImage(redImg, locX, locY, wScale, hScale);
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
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width}, function (data) {
        console.log(data);
        mapRender(data);
    }, "json");
}

/**
 * Set the easy level
 */
function getEasyLevel() {
    location.reload();
}

/**
 * Set the hard level
 */
function getHardLevel() {
    $.post("/level", {level: "hard"}, function (data) {
        console.log(data);
        mapRender(data);
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
