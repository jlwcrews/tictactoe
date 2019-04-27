package no.jlwcrews.tictactoe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*



class GameFragment : Fragment(), View.OnClickListener {

    var currentPlayer: String = ""
    var moveCounter: Int = 0
    var movesMade: MutableMap<Int, String> = mutableMapOf()
    val winningMove1 = listOf(1,2,3)
    val winningMove2 = listOf(4,5,6)
    val winningMove3 = listOf(7,8,9)
    val winningMove4 = listOf(1,4,7)
    val winningMove5 = listOf(3,6,9)
    val winningMove6 = listOf(2,5,8)
    val winningMove7 = listOf(1,5,9)
    val winningMove8 = listOf(3,5,7)
    val winningMoves = listOf(winningMove1, winningMove2, winningMove3, winningMove4, winningMove5, winningMove6, winningMove7, winningMove8)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewGame()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)

        buttonReset.setOnClickListener{setupNewGame()}
    }

    override fun onClick(v: View) {
        val button = v.findViewById<Button>(v.id)
        val square = button.tag.toString().toInt()

        if (validateMove(square)){
            moveCounter++
            movesMade.set(square, currentPlayer)
            button.text = currentPlayer
            counterTextView.text = "$moveCounter ${getString(R.string.moves)}"
            if(checkForWin()){
                Toast.makeText(this.context, currentPlayer + "Wins", Toast.LENGTH_LONG).show()
            } else {
                when(currentPlayer){
                    "X" -> currentPlayer = "O"
                    "O" -> currentPlayer = "X"
                    else -> println("What the hell?")
                }
            }
        } else {
            Toast.makeText(this.context, "Invalid selection", Toast.LENGTH_LONG).show()
        }
    }

    fun setupNewGame(){
        currentPlayer = "X"
        moveCounter = 0
        movesMade.clear()
    }

    fun validateMove(square: Int): Boolean {
        if (movesMade.containsKey(square)){
            return false
        }
        return true
    }

    fun checkForWin(): Boolean {
        val currentPlayerMoves = movesMade.filterValues { it == currentPlayer }.keys.toList()
        if (currentPlayerMoves.size < 3) return false
        for (move in winningMoves){
            if (currentPlayerMoves.containsAll(move)){
                return true
            }
        }
        return false
    }
}
