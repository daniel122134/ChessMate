import {YoffeeElement, createYoffeeElement, html} from "../libs/yoffee/yoffee.min.js";

const bishop = "♜";
const knight = "♞";
const rook = "♝";
const queen = "♛";
const king = "♚";
const pawn = "♟";

createYoffeeElement("home-page", class extends YoffeeElement {
    render() {
        return html(this.state)`
<style>
    :host {
        display: flex;
        flex-direction: column;
        height: inherit;
        align-items: center;
        overflow-y: auto;
    }
    
    #title-block-container {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 700px;
        padding-top: 100px;
    }
    
    #logo {
        width: 240px;
        padding-right: 40px;
    }
    
    #title-text-container {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }
    
    #title-text {
        font-size: 66px;
        font-weight: bold;
        color: var(--secondary-color);
        padding-bottom: 20px;
    }
    
    #title-description {
        font-size: 24px;
        padding-bottom: 28px;
    }
    
    #buttons-container {
        display: flex;    
    }
    
    #get-started-button {
        font-size: 20px;
        padding: 16px 19px;
        background-color: var(--secondary-color);
        margin-right: 20px;
    }
    
    #linkedin-button {
        font-size: 20px;
        padding: 16px 19px;
        background-color: var(--text-color-weak-3);
    }
    
    #linkedin-button > #linkedin-icon {
        margin-left: 10px;
    }
    
    @media (max-width: 800px) {
        #title-block-container {
            flex-direction: column;
            width: 350px;
            padding-top: 50px;
        }
        
        #logo {
            width: 140px;
            padding-right: 0;
            padding-bottom: 20px;
        }
        
        #title-text-container {
            align-items: center;
        }
        
        #title-text {
            font-size: 42px;
        }
        
        #title-description {
            text-align: center;
        }
        

    }
  
    
    .chess-board { border-spacing: 0; border-collapse: collapse;color: black }
    .chess-board th { padding: .5em; }
    .chess-board th + th { border-bottom: 1px solid #000; }
    .chess-board th:first-child,
    .chess-board td:last-child { border-right: 1px solid #000; }
    .chess-board tr:last-child td { border-bottom: 1px solid; }
    .chess-board th:empty { border: none; }
    .chess-board td { width: 1.5em; height: 1.5em; text-align: center; font-size: 32px; line-height: 0;}
    .chess-board .light { background: #eee; }
    .chess-board .dark { background: #aaa; }

</style>
<div id="title-block-container">
    <div id="title-text-container">
        <div id="title-text">ChessMate</div>
    </div>
</div>

<table class="chess-board">
    <tbody>
        
        
        
    </tbody>
</table>


        `
    }
    
});


import "./mark-down.js"
