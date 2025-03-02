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

### Config

Command based configuration interface.

#### Configurables

What can be modified with this interface:

- Keywords and phrases to check for
- What to do when found

## Technologies

Java 8

Gradle 4.5