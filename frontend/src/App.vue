<template>
    <div id="board">
        <b-tabs>
            <b-tab v-for="board in dummyBoard" :key="board.id" :title="board.name" active>
                <b-container fluid>
                    <b-row>
                        <b-col :cols="storyTitleCols"></b-col>
                        <b-col v-for="state in board.states" :key="state.id">{{ state.name }}</b-col>
                    </b-row>
                    <b-row v-for="story in board.stories" :key="story.id">
                        <b-col :cols="storyTitleCols">
                            <b>{{ story.title }}</b>
                            <br>
                            {{ story.description }}
                        </b-col>
                        <b-col v-for="state in board.states" :key="state.id">
                            <b-card v-show="insertTask(state,task)" :title="task.title" v-for="task in story.tasks"
                                    :key="task.id">
                                <div v-on:click="showTaskModal(board, task)">
                                    {{insertTask(state,task)}}
                                </div>
                                <br>
                                <b-button v-on:click="moveTaskLeft(board,task)" size="sm" variant="secondary"><-
                                </b-button>
                                <b-button v-on:click="moveTaskRight(board, task)" size="sm" variant="secondary">->
                                </b-button>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-container>
            </b-tab>
        </b-tabs>
        <b-modal  v-bind="taskModalDataContainer" ref="taskModal" :title="taskModalDataContainer.title">
            <b-container>
                <b-row>
                    <b-col>
                        <b>Description</b>
                        <br>
                        {{taskModalDataContainer.description}}
                    </b-col>
                    <b-col cols="4">
                        <b>Assigness:</b>
                        <ul>
                            <li v-for="assignee in taskModalDataContainer.assignee">
                                {{assignee.name}}
                            </li>
                        </ul>
                    </b-col>
                </b-row>
            </b-container>
            <b-container slot="modal-footer"><b-button size="sm" variant="primary" v-on:click="hideModal">Close</b-button></b-container>
        </b-modal>
    </div>


</template>

<script>
    export default {
        name: 'app',
        data() {
            return {
                //DO NOT DELETE, NO DUMMYOBJECTS
                storyTitleCols: 1,
                taskModalDataContainer: {},
                //UNTIL HERE

                users: [
                    {
                        id: 1,
                        name: "John Doe",
                        role: "Developer"
                    },
                    {
                        id: 2,
                        name: "Aria Stark",
                        role: "QA"
                    }

                ],
                dummyBoard:
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
            },
            showTaskModal: function (board, task) {
                this.taskModalDataContainer = task;
                this.taskModalDataContainer.id = this.taskModalDataContainer.index;
                delete this.taskModalDataContainer.id;

                let users = this.users;
                let container = this.taskModalDataContainer;
                let assignees = [];
                container.assignee.forEach(function (assignee) {
                    console.log(assignee)
                    users.forEach(function (user) {
                        if (assignee === user.id) {
                            assignees.push(user);
                        }
                    })
                });
                container.assignee = assignees;
                this.taskModalDataContainer = container;
                this.$refs.taskModal.show();
            },
            moveTaskRight: function (board, task) {
                if (task.state < board.states.length) {
                    task.state += 1;
                }
            },
            moveTaskLeft: function (board, task) {
                if (task.state > 1) {
                    task.state -= 1;
                } else {
                    return false;
                }
            },
            hideModal: function() {
                this.$refs.taskModal.hide();
            }
        },
        computed: {}
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

    taskCard:hover {
        background-color: lightgrey;
        cursor: pointer;
    }
</style>
