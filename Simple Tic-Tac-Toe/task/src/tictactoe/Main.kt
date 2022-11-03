package tictactoe

var board: MutableList<MutableList<Char>> = MutableList(3) { MutableList(3) { ' ' } }

fun main() {
    printBoard()

    var gameCondition = boardState()
    var move = 'X'
    while (gameCondition == "Game not finished") {
        val coord = readln()
        var moveAnalysis = analyze(coord, move)
        if (moveAnalysis != "") {
            println(moveAnalysis)
        }
        if (move == 'X') {
            move = 'O'
        } else {
            move = 'X'
        }
        printBoard()
        gameCondition = boardState()
    }
    print(gameCondition)
}

fun analyze(coord: String, move: Char): String {
    val ch = coord.toCharArray()
    if (!ch[0].isDigit() || !ch[2].isDigit()) {
        return "You should enter numbers!"
    }
    if (ch[0].digitToInt() < 1 || ch[0].digitToInt() > 3 || ch[2].digitToInt() < 1 || ch[2].digitToInt() > 3) {
        return "Coordinates should be from 1 to 3!"
    }
    var piece = board[ch[0].digitToInt() - 1][ch[2].digitToInt() - 1]
    if (piece == 'X' || piece == 'O') {
        return "This cell is occupied! Choose another one!"
    }
    board[ch[0].digitToInt() - 1][ch[2].digitToInt() - 1] = move
    return ""
}

fun boardState(): String {
    var res = "Game not finished"
    var finish = true
    var win = 0
    if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') {
        res = "${board[1][1]} wins"
        win++
    }
    if ((board[0][2] == board[1][1] && board[2][0] == board[1][1]) && board[0][2] != ' ') {
        res = "${board[0][2]} wins"
        win++
    }
    for (i in 0..2) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
            res = "${board[i][0]} wins"
            win++
        }
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
            res = "${board[0][i]} wins"
            win++
        }
        if ('_' in board[i] || ' ' in board[i]) {
            finish = false
        }
    }
    if (finish && win < 1) {
        res = "Draw"
    }
    if (win > 1) {
        res = "Impossible"
    }
    return res
}

fun printBoard() {
    println("---------")
    println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
    println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
    println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
    println("---------")
}