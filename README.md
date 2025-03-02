# Transcript

## Design Document

This mod reads and re-formats chat messages based on configuration.

## Features

Stuff we want.

### Chat-reading

Use the chat event system to read chat messages. If they contain a string that
should trigger an alert, execute said alert.

### Alerts

Alerts triggered:

- Sound cues
- Text

#### Alert event

It uses the event system for Forge.

It holds the type (ping, highlight, etc.), and the said string.

Possible Alerts/Notifications are:

In chat:

- Highlighting (in various colors)
- Underlining
- Obfuscation
- Strikethrough
- Bold
- Italic

Outside chat:

- Ping
- Title

### Config

Command based configuration interface.

#### Command

Execution: /transcript

Usage:

- /transcript add \[alert1] \[alert2] \<phrase>
    - Adds word/phrase to the list of targets
    - Plays the notifications stored in the 2nd subcommand
- /transcript remove> <phrase>
    - Removes a target
- /transcript <alert> <enable|disable>
    - Toggles whether alerts should be played in general
- /transcript list
    - Lists all targets
- /transcript reload
    - Reload the cache in case the config file is manually edited

#### Configurables

What can be modified with this interface:

- Keywords and phrases to check for
- What to do when found

## Technologies

Java 8

Gradle 4.5