package jetbrains.kotlin.course.alias.results

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService

typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Result cannot be empty")
        }
        if (result.any { it.id !in TeamService.teamsStorage.keys }) {
            throw IllegalArgumentException("Invalid team IDs in result")
        }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}
