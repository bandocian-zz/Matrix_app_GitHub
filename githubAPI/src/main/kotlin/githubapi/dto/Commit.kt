package githubapi.dto

data class CommitData(val stats: Stats)

data class Stats(val total: Int = 0, val additions: Int = 0, val deletions: Int = 0)

