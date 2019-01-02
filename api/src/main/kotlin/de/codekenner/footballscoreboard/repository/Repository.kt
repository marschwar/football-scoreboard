package de.codekenner.footballscoreboard.repository

interface Repository<T> {
    fun save(document: T)
}