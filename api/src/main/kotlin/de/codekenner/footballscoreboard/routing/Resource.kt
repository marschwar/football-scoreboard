package de.codekenner.footballscoreboard.routing

class Resource(val controller: Controller, only: List<Action> = emptyList()) {
    val actions: List<Action> = if (only.isNullOrEmpty()) Action.values().toList() else only
}