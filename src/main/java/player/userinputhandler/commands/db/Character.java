package player.userinputhandler.commands.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import player.dndcharacter.DndCharacter;

@Entity
@Table(name = "characters")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "characters_seq_gen")
    @SequenceGenerator(name = "characters_seq_gen", sequenceName = "characters_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "character")
    @JdbcTypeCode(SqlTypes.JSON)
    private DndCharacter dndCharacter;
}
