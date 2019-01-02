package de.codekenner.footballscoreboard.repository

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import de.codekenner.footballscoreboard.domain.Team
import org.apache.logging.log4j.LogManager

class TeamsRepository : Repository<Team> {

    private val table: Table by lazy {
        LOG.info("getting table")
        Connection.dynamoDB().getTable(TABLE_NAME)
    }

    override fun save(document: Team) {
        table.putItem(document.asItem())
        LOG.info("item saved")
    }

    private fun Team.asItem(): Item =
            Item()
                    .withPrimaryKey(Team::id.name, id)
                    .withString(Team::name.name, name)

    companion object {
        private const val TABLE_NAME = "Teams"
        private val LOG = LogManager.getLogger(TeamsRepository::javaClass)

    }
}