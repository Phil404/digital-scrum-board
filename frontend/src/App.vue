<template>
    <div id="board">
        <b-tabs>
            <b-tab v-for="board in dumyBoard" :key="board.id" :title="board.name" active>
                <b-container fluid>
                    <b-row>
                        <b-col></b-col>
                        <b-col v-for="state in board.states" :key="state.id">{{ state.name }}</b-col>
                    </b-row>
                    <b-row v-for="story in board.stories" :key="story.id">
                        <b-col>{{ story.title }}</b-col>
                        <b-col v-for="state in board.states" :key="state.id">
                            <b-card v-show="insertTask(state,task)" :title="task.title" v-for="task in story.tasks" :key="task.id">
                                {{insertTask(state,task)}}
                                <br >
                                <b-button :size="sm" :variant="secondary"><-</b-button>
                                <b-button :size="sm" :variant="secondary">-></b-button>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-container>
            </b-tab>
        </b-tabs>
    </div>
</template>

<script>
    export default {
        name: 'app',
        data() {
            return {
                dumyBoard:
                    [
                        {
                            id: 1,
                            name: "superboard",
                            states: [
                                {
                                    id: 1,
                                    name: "in Progress"
                                },
                                {
                                    id: 2,
                                    name: "QA"
                                },
                                {
                                    id: 3,
                                    name: "Done"
                                }
                            ],
                            stories: [
                                {
                                    id: 1,
                                    title: "story1",
                                    description: "do shit faster",
                                    startDate: 23 - 12 - 1993,
                                    creatDate: 24 - 12 - 1993,
                                    doneDate: 25 - 12 - 1993,
                                    tasks: [
                                        {
                                            id: 1,
                                            state: 1,
                                            assignee: [1, 2],
                                            title: "task #1",
                                            description: "we need to do some stuff",
                                            startDate: 23 - 12 - 1994,
                                            creatDate: 24 - 12 - 1994,
                                            doneDate: 25 - 12 - 1994
                                        },
                                        {
                                            id: 3,
                                            state: 3,
                                            assignee: [1, 2],
                                            title: "task #1",
                                            description: "we need to do some stuff",
                                            startDate: 23 - 12 - 1994,
                                            creatDate: 24 - 12 - 1994,
                                            doneDate: 25 - 12 - 1994
                                        }
                                    ]
                                },
                                {
                                    id: 3,
                                    title: "story2",
                                    description: "do stuff faster",
                                    startDate: 12 - 12 - 1993,
                                    creatDate: 13 - 12 - 1993,
                                    doneDate: 14 - 12 - 1993,
                                    tasks: [
                                        {
                                            id: 2,
                                            state: 2,
                                            assignee: [1, 2],
                                            title: "Dummytask",
                                            description: "fix bug",
                                            startDate: 23 - 12 - 1994,
                                            creatDate: 24 - 12 - 1994,
                                            doneDate: 25 - 12 - 1994
                                        }
                                    ]
                                }

                            ]
                        },
                        {
                            id: 2,
                            rows: [1, 2, 3],
                            columns: [7, 6, 5],
                            name: "überboard"
                        }
                    ]
            }
        },
        methods: {
            insertTask: function (state, task) {
                if (state.id === task.state) {
                    return task.description
                }
                return false;
            }
        },
        mounted() {

        }

    }
</script>

<style lang="scss">
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    h1, h2 {
        font-weight: normal;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
