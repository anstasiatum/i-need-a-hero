# I Need A Hero

## 📜 Summary
- [About](#-about)
- [Stack](#-stack)
- [Functionality](#-functionality)
- [Contribute](#-contribute)
- [Acknowledgements](#-acknowledgements)

## 🧙‍♀️ About
"I Need A Hero" is a Telegram bot that supports a step-by-step creation of DnD characters. It asks users questions and generates heroes based on the answers. In the end, player gets an editable PDF of their character. 

## 📚 Stack
<div align="center">
  <table>
    <tr>
      <!-- Первая строка -->
      <td align="center" width="110">
        <a href="https://www.java.com" target="_blank">
          <img src="readmemedia/java-original-wordmark.svg" width="48" height="48" alt="Java" />
        </a>
        <br>Java 21
      <td align="center" width="110">
        <a href="https://www.postgresql.org/" target="_blank">
          <img src="readmemedia/postgresql-plain.svg" width="48" height="48" alt="Postgres 17" />
        </a>
        <br>Postgres 17
      </td>
      </td>
      <td align="center" width="110">
        <a href="https://hibernate.org/" target="_blank">
          <img src="readmemedia/hibernate-original-wordmark.svg" width="48" height="48" alt="Hibernate" />
        </a>
        <br>Hibernate
      </td>
      <td align="center" width="110">
        <a href="https://maven.apache.org/" target="_blank">
          <img src="readmemedia/maven-original-wordmark.svg" width="48" height="48" alt="Maven" />
        </a>
        <br>Maven
      </td>
    </tr>
    <tr>
      <!-- Вторая строка -->
      <td align="center" width="110">
        <a href="https://github.com/" target="_blank">
          <img src="readmemedia/github-original-wordmark.svg" width="48" height="48" alt="GitHub" />
        </a>
        <br>GitHub
      </td>
      <td align="center" width="110">
        <a href="https://web.telegram.org/" target="_blank">
          <img src="readmemedia/Telegram.svg" width="48" height="48" alt="Telegram" />
        </a>
        <br>Telegram
      </td>
          <td align="center" width="110">
        <a href="https://junit.org/junit5/" target="_blank">
          <img src="readmemedia/junit-original.svg" width="48" height="48" alt="JUnit 5" />
        </a>
        <br>JUnit 5
      </td>
      <td align="center" width="110">
        <a href="https://site.mockito.org/" target="_blank">
          <img src="readmemedia/mockito.png" width="48" height="48" alt="Mockito" />
        </a>
        <br>Mockito
      </td>
    </tr>
  </table>
</div>

Additional libraries:
◦ Lombok
◦ Java-telegram-bot-api
◦ Apache pdf box
◦ Jackson Mapper

## ✨Funcitonality

#### Create
To start the hero creation process use the `/newhero` command.
The bot generates a character based on the user's answers. It can automatically roll for basic hero characteristics or use the dice values entered by the player. It also modifies the character by race, class, and backstory selected by the user.
However, there are some *limitations* as to what the bot can do:

- English only (otherwise the PDF is not generated)
- DnD 5e 2014 only
- No spells 
- 1st level only

#### Read
All player's characters are stored in the DB and can be accessed anytime using the `/printhero` command. It sends the PDF charcater sheet to the user.

#### Update
Unfortunately, custom editing is not supported for now

#### Delete
Player can delete their hero anytime using the `/dismisshero` command.

## ✍️ Contribute
I would love to get your contribution, whether it's in form of bug reports, Requests for Enhancement (RFE), documentation, or code patches. Any feedback is more than welcome!

## 🧙 Acknowledgements
Big thanks to Oleg Rekin for guiding me through the process of building this bot!
