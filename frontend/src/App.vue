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
                            <b-btn v-on:click="showAddTaskModal(story, board)" size="sm" variant="primary"> Add Task
                            </b-btn>
                            <b-btn v-on:click="showStoryModal(board, story)" size="sm" variant="secondary">Info</b-btn>
                        </b-col>
                        <b-col v-for="state in board.states" :key="state.id">
                            <b-card class="taskCard" v-show="insertTask(state,task)" :title="task.title"
                                    v-for="task in story.tasks"
                                    :key="task.id">
                                <div class="taskCardText" v-on:click="showTaskModal(board, task)">
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
        <b-button class="addStoryButton" size="sm" variant="primary" >Add Story</b-button>


        <b-modal size="lg" v-bind="taskModalDataContainer" ref="taskModal" :title="taskModalDataContainer.title">
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
                            <li v-for="assignee in taskModalDataContainer.assignees">
                                {{assignee.name}}
                            </li>
                        </ul>
                    </b-col>
                </b-row>
            </b-container>
            <b-container slot="modal-footer">
            </b-container>
        </b-modal>

        <b-modal size="lg" v-bind="storyModalDataContainer" ref="storyModal" :title="storyModalDataContainer.title">
            <b-container>
                <b-row>
                    <b-col>
                        <b>Description</b>
                        <br>
                        {{storyModalDataContainer.description}}
                    </b-col>
                </b-row>
                <hr>
                <b-row>
                    <b-col>
                        <b>Start Date</b><br>{{ storyModalDataContainer.startDate}}
                    </b-col>
                    <b-col>
                        <b>Create Date</b><br>{{ storyModalDataContainer.startDate}}
                    </b-col>
                    <b-col>
                        <b>Done Date</b><br>{{ storyModalDataContainer.startDate}}
                    </b-col>
                </b-row>
            </b-container>
            <b-container slot="modal-footer">
            </b-container>
        </b-modal>


        <b-modal size="lg" v-bind="addTaskDataContainer" ref="addTaskModal" title="Add a Task">
            <b-container>
                <div>
                    <b-form-input v-model="addTaskDataContainer.taskName" type="text" placeholder="Task Name"></b-form-input>
                    <b-form-textarea v-model="addTaskDataContainer.description" type="text" placeholder="Task Description" :rows="3"
                                     :max-rows="6">>
                    </b-form-textarea>
                    <b-form-select v-model="addTaskDataContainer.assignee" :options="addTaskDataContainer.users"
                                   class="mb-3"></b-form-select>
                </div>
            </b-container>
            <b-container slot="modal-footer">
                <b-button v-on:click="addTask()" variant="primary">Add to Story</b-button>
            </b-container>
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
                storyModalDataContainer: {},
                addTaskDataContainer: {},
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
                                },
                                {
                                    id: 4,
                                    name: "Live"
                                }
                            ],
                            stories: [
                                {
                                    id: 1,
                                    title: "story1",
                                    description: "do shit faster",
                                    startDate: "23 - 12 - 1993",
                                    creatDate: "24 - 12 - 1993",
                                    doneDate: "25 - 12 - 1993",
                                    tasks: [
                                        {
                                            id: 1,
                                            state: 1,
                                            assignees: [1, 2],
                                            title: "task #1",
                                            description: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat.",
                                            startDate: "23 - 12 - 1994",
                                            creatDate: "24 - 12 - 1994",
                                            doneDate: "25 - 12 - 1994"
                                        },
                                        {
                                            id: 3,
                                            state: 3,
                                            assignees: [1, 2],
                                            title: "task #1",
                                            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus consectetur facilisis eleifend. Sed neque tortor, condimentum non scelerisque feugiat, iaculis ut lectus.",
                                            startDate: "23 - 12 - 1994",
                                            creatDate: "24 - 12 - 1994",
                                            doneDate: "25 - 12 - 1994"
                                        }
                                    ]
                                },
                                {
                                    id: 3,
                                    title: "story2",
                                    description: "do stuff faster",
                                    startDate: "12 - 12 - 1993",
                                    creatDate: "13 - 12 - 1993",
                                    doneDate: "14 - 12 - 1993",
                                    tasks: [
                                        {
                                            id: 2,
                                            state: 2,
                                            assignees: [1, 2],
                                            title: "Dummytask",
                                            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus consectetur facilisis eleifend.",
                                            startDate: "23 - 12 - 1994",
                                            creatDate: "24 - 12 - 1994",
                                            doneDate: "25 - 12 - 1994"
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

                let container = this.taskModalDataContainer;
                let users = this.users;
                console.log(task.assignees);
                let assignees = [];
                task.assignees.forEach(function (assignee) {
                    users.forEach(function (user) {
                        if (assignee === user.id) {
                            assignees.push(user);
                        }
                    })
                });
                container.assignees = assignees;
                this.taskModalDataContainer = Object.assign({}, container);
                this.$refs.taskModal.show();
            },
            showStoryModal: function (board, story) {
                this.storyModalDataContainer = story;
                this.$refs.storyModal.show();
            },
            showAddTaskModal: function (story, board) {
                this.addTaskDataContainer.story = story;
                this.addTaskDataContainer.board = board;
                let userNames = [];
                this.users.forEach(function (user) {
                    let nameObj = {value: user.name, text: user.name};
                    userNames.push(nameObj)
                });
                console.log(userNames);
                this.addTaskDataContainer.users = userNames;
                this.$refs.addTaskModal.show();
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
            addTask() {
                let task = {
                    id: null,
                    state: 1,
                    assignees: this.addTaskDataContainer.assignee,
                    title: this.addTaskDataContainer.taskName,
                    description: this.addTaskDataContainer.description,
                    startDate: "23 - 12 - 1994",
                    creatDate: "24 - 12 - 1994",
                    doneDate: "25 - 12 - 1994"
                };
                console.log(task)
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

    .taskCard:hover {
        background-color: #b3b3b3;
    }

    .taskCardText:hover {
        cursor: pointer;
        color: whitesmoke;
    }

    .addStoryButtn {
        position: fixed;
        bottom: 0;
        left: 0;
    }
</style>
