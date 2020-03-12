package githubapi.core

import githubapi.dto.Stats

object WeightCalculator{

    fun calculateWeight(stats: List<Stats>) : Int{
        var additions = 0
        var deletions = 0
        var total = 0
        stats.forEach{
            additions += it.additions
            deletions += it.deletions
            total += it.total
        }
        // Completely magic number:
        return additions * 10 + deletions * 9
    }

}